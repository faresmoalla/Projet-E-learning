<?php



namespace App\Controller;
/*
session_start();

header('http://127.0.0.1/login');
exit();*/


use Twilio\Rest\Client;
use App\Entity\Utilisateur;
use App\Form\EntrepreneurType;
use App\Form\FormateurType;
use App\Form\InscriptionType;
use App\Form\LoginType;
use App\Form\MembreType;
use App\Form\UtilisateurType;
use App\Repository\UtilisateurRepository;
use phpDocumentor\Reflection\Types\Null_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use function PHPUnit\Framework\equalTo;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;






class UtilisateurController extends AbstractController
{



    /**
     * @Route("/utilisateur", name="utilisateur")
     */
    public function index(): Response
    {
        return $this->render('utilisateur/index.html.twig', [
            'controller_name' => 'UtilisateurController',
        ]);
    }

    /**
     * @Route("/ajouterUtilisateur", name="ajouterUtilisateur")
     * @param Request $request
     * @return Response
     */
    public function ajouterUtilisateur(Request $request, UserPasswordEncoderInterface $passwordEncoder): Response
    {

        $Utilisateur = new Utilisateur ();
        $form= $this->createForm(InscriptionType::class,$Utilisateur);
        $form->handleRequest($request);


        if($form->isSubmitted() && $form->isValid()){

            $num= $Utilisateur->getUtilisateurphone();
            $n='+216'.$num;



            //$mdp=$form->get('utilisateurmdp')->getData();
           // $hashmdp=sha1($mdp);
            // $Utilisateur->setUtilisateurmdp($hashmdp);


            //$plainPassword =$form->get('utilisateurmdp')->getData();;
            //$encoded = $encoder->encodePassword($Utilisateur, $plainPassword);
            //$Utilisateur->setUtilisateurmdp($encoded);
            $Utilisateur->setUtilisateurmdp(
                $passwordEncoder->encodePassword(
                    $Utilisateur,
                    $form->get('utilisateurmdp')->getData()
                )
            );
            $em= $this->getDoctrine()->getManager();
            $em->persist($Utilisateur );
            $em->flush();
            $this->getDoctrine()->getManager()->flush();
            $account_sid = 'AC04fedb666177e902b410a42d0b4614b9';
            $auth_token = 'e19969ecb5f0279d8539e03f2c414f40';
            $client = new Client($account_sid, $auth_token);

            $verification = $client->verify->v2->services("VA43d502871f086dd1dc62cb5fccfef0b2")
                ->verifications
                ->create($n, "sms");

            print($verification->status);
            $user_params = [
                'utilisateurprenom' => $request->request->get('utilisateurprenom'),
                'utilisateurnom' => $request->request->get('utilisateurnom'),
                'utilisateurgenre' => $request->request->get('utilisateurgenre'),
                'utilisateurddn' => $request->request->get('utilisateurddn'),
                'utilisateuradresseemail' => $request->request->get('utilisateuradresseemail'),
                'utilisateurphone' => $request->request->get('utilisateurphone'),
                'utilisateurmdp' => $request->request->get('utilisateurmdp'),
                'utilisateurrole' => $request->request->get('utilisateurrole'),
                'authy_id' => $Utilisateur->getId(),
            ];

            $this->get('session')->set('user', $user_params);
            return $this->render("utilisateur/verify.html.twig");


        }
        return $this->render("utilisateur/Inscription.html.twig",array("formulaire"=>$form->createView()));
    }
    /**
     * @Route("/supprimerUtilisateur/{id}",name="supprimerUtilisateur")
     */
    public function delete(FlashyNotifier $flashy,$id)
    {
        $club= $this->getDoctrine()->getRepository(Utilisateur::class)
            ->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($club);
        $em->flush();
        $flashy->success('utilisateur supprimé avec succés ');

        return $this->redirectToRoute("AfficheUtilisateurs");
    }

    /**
     * @Route("/modifierUtilisateur/{id}",name="modifierUtilisateur")
     * @param Request $request
     * @param $id
     * @param $passwordEncoder
     * @return \Symfony\Component\HttpFoundation\RedirectResponse|Response
     */
    public function update(Request $request, $id,UserPasswordEncoderInterface $passwordEncoder)
    {
        $session =  $_SESSION  ; //$request->getSession();
        $session->start();
        $session->save();
        $Utilisateur = $this->getDoctrine()->getRepository(Utilisateur::class)->find($id);

        //$Utilisateur=$this->getUser();
        //$session->set('user', $Utilisateur);


        $Utilisateur->setutilisateurmdp("");
        if(strcmp($Utilisateur->getUtilisateurrole(),"Membre")==0) {
            $form = $this->createForm(MembreType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $Utilisateur->setUtilisateurmdp(
                    $passwordEncoder->encodePassword(
                        $Utilisateur,
                        $form->get('utilisateurmdp')->getData()
                    )
                );
                $imageFile = $form->get('utilisateurpdp')->getData();
                if ($imageFile) {
                    $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                    $newFilename = $originalFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();
                    try {
                        $imageFile->move(
                            'front\web\images',
                            $newFilename
                        );
                    } catch (FileException $e) {
                    }
                    $Utilisateur->setutilisateurpdp($newFilename);
                }
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("acceuil");
            }

            return $this->render("utilisateur/updateMembre.html.twig", array("formulaire" => $form->createView()));
        }
        else if(strcmp($Utilisateur->getUtilisateurrole(),"Formateur")==0) {
            $form = $this->createForm(FormateurType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $Utilisateur->setUtilisateurmdp(
                    $passwordEncoder->encodePassword(
                        $Utilisateur,
                        $form->get('utilisateurmdp')->getData()
                    )
                );
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("acceuil");
            }
            return $this->render("utilisateur/updateFormateur.html.twig", array("formulaire" => $form->createView()));
        }
        else if(strcmp($Utilisateur->getUtilisateurrole(),"Entrepreneur")==0) {
            $form = $this->createForm(EntrepreneurType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $Utilisateur->setUtilisateurmdp(
                    $passwordEncoder->encodePassword(
                        $Utilisateur,
                        $form->get('utilisateurmdp')->getData()
                    )
                );
                $imageFile = $form->get('utilisateurpdp')->getData();
                if ($imageFile) {
                    $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                    $newFilename = $originalFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();
                    try {
                        $imageFile->move(
                            'front\web\images',
                            $newFilename
                        );
                    } catch (FileException $e) {
                    }
                    $Utilisateur->setutilisateurpdp($newFilename);
                }
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("acceuil");
            }
            return $this->render("utilisateur/updateEntrepreneur.html.twig", array("formulaire" => $form->createView()));
        }else  {
            $form = $this->createForm(UtilisateurType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $Utilisateur->setUtilisateurmdp(
                    $passwordEncoder->encodePassword(
                        $Utilisateur,
                        $form->get('utilisateurmdp')->getData()
                    )
                );
                $imageFile = $form->get('utilisateurpdp')->getData();
                if ($imageFile) {
                    $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                    $newFilename = $originalFilename . '-' . uniqid() . '.' . $imageFile->guessExtension();
                    try {
                        $imageFile->move(
                            'front\web\images',
                            $newFilename
                        );
                    } catch (FileException $e) {
                    }
                    $Utilisateur->setutilisateurpdp($newFilename);
                }
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("acceuilAdmin");
            }
            return $this->render("utilisateur/updateAdmin.html.twig", array("formulaire" => $form->createView()));
        }
    }
    /**
     * @Route("/AfficheUtilisateurs",name="AfficheUtilisateurs")
     */
    public function afficher(UtilisateurRepository $UtilisateurRepository): Response
    {
        // $repo =$this->getDoctrine()->getManager();
        // $Enseigants=$repo ->getRepository("App\Entity\Enseignant")->findAll();
        return $this->render('utilisateur/show.html.twig', [
            'Utilisateurs' => $UtilisateurRepository ->findAll()
        ]);
    }
    /**
     * @Route("/acceuil", name="acceuil")
     */
    public function acceuil(Request $request): Response
    {
        $session = $request->getSession();
        $session->start();
        $session->save();
        $Utilisateur=$this->getUser();
        $session->set('user', $Utilisateur);

        $email=$this->getUser()->getUsername();
        $Utilisateur = $this->getDoctrine()->getRepository(Utilisateur::class)->findOneBy(['utilisateuradresseemail' => $email]);
        $role=$Utilisateur->getutilisateurrole();
            if(strcmp($role,"Admin")==0){
                return $this->render("utilisateur/acceuilAdmin.html.twig");
            }else{
                return $this->render("utilisateur/acceuil.html.twig",array('session'=>$_SESSION));
            }

        //return $this->redirectToRoute("utilisateur");
       // return $this->render("utilisateur/acceuilAdmin.html.twig");

        }

    /**
     * @Route("/verify", name="verify")
     */
    public function verifyCode(Request $request)
    {   $data = $this->get('session')->get('user');
        $sid = "AC04fedb666177e902b410a42d0b4614b9";
        $token = "e19969ecb5f0279d8539e03f2c414f40";
        $twilio = new Client($sid, $token);
        $num=$data['utilisateurphone'];
        $n='+216'.$num;
        $verification_check = $twilio->verify->v2->services("VA43d502871f086dd1dc62cb5fccfef0b2")
            ->verificationChecks
            ->create($request->query->get('verify_code'), // code
                ["to" => $n]
            );

        $status=$verification_check->status;

        if (strcmp($status,'approved'))
        {return $this->redirectToRoute("app_login");
        }
        else{
            $this->addFlash(
                'error',
                'Verification code is incorrect'
            );
        }
    }

    public function findStudentByEmail($email){
        return $this->createQueryBuilder('utilisateur')
            ->where('utilisateur.utilisateurAdresseEmail LIKE :email')
            ->setParameter('email', '%'.$email.'%')
            ->getQuery()
            ->getResult();
    }

    /**
     * @Route("/searchUtilisateur ", name="searchUtilisateur")
     */
    public function searchUtilisateur(Request $request,NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Utilisateur::class);
        $requestString=$request->get('searchValue');
        $utilisateur = $repository->findStudentByEmail($requestString);
        $jsonContent = $Normalizer->normalize($utilisateur, 'json',['groups'=>'utilisateurs']);
        $retour=json_encode($jsonContent);
        return new Response($retour);

    }







}
