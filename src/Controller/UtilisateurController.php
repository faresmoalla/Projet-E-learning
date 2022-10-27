<?php



namespace App\Controller;
/*
session_start();

header('http://127.0.0.1/login');
exit();*/


use App\Form\AdminType;
use App\Form\MotDePasseOublieType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Twilio\Exceptions\TwilioException;
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
        return $this->render('utilisateur/show.html.twig', [
            'controller_name' => 'UtilisateurController',
        ]);
    }

    /**
     * @Route("/ajouterUtilisateur", name="ajouterUtilisateur")
     * @param Request $request
     * @return Response
     */
    public function ajouterUtilisateur(FlashyNotifier $flashy,Request $request, UserPasswordEncoderInterface $passwordEncoder): Response
    {

        $Utilisateur = new Utilisateur ();
        $form= $this->createForm(InscriptionType::class,$Utilisateur);
        $form->handleRequest($request);


        if($form->isSubmitted() && $form->isValid()){





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
            /*
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

            $this->get('session')->set('user', $user_params);*/
/*
            $account_sid = 'AC04fedb666177e902b410a42d0b4614b9';
            $auth_token = 'e19969ecb5f0279d8539e03f2c414f40';
            $twilio_number = "+12724442986";
            $name=$Utilisateur->getUtilisateurnom();
            $num= $Utilisateur->getUtilisateurphone();
            $n='+216'.$num;
            $client = new Client($account_sid, $auth_token);
            $client->messages->create(
            // Where to send a text message (your cell phone?)
                $n,
                array(
                    'from' => $twilio_number,
                    'body' => 'welcome back'.' '.$name.' '.'to 9arini'
                )
            );
            */
            return $this->render("acceuil\Acceuil2.html.twig");


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
        $Utilisateur = $this->getDoctrine()->getRepository(Utilisateur::class)->find($id);
        if(strcmp($Utilisateur->getUtilisateurrole(),"Membre")==0) {
            $form = $this->createForm(MembreType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                //recuperation des images transmises
                $image = $form->get('utilisateurpdp')->getData();
                // Boucle sur les images

                // on genere un nouveau nom de fichier avec md5. guessExtension recupere l'extension du fichier
                $fichier = md5(uniqid()) . '.' . $image->guessExtension();
                //On copie le fichier dans le dossier upload
                $image->move(
                    $this->getParameter('upload_directory'),
                    $fichier
                );

                // on stocke l'image dans la bdd (son nom)

                $Utilisateur->setUtilisateurpdp($fichier);



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
                //recuperation des images transmises
                $image = $form->get('utilisateurpdp')->getData();
                // Boucle sur les images

                // on genere un nouveau nom de fichier avec md5. guessExtension recupere l'extension du fichier
                $fichier = md5(uniqid()) . '.' . $image->guessExtension();
                //On copie le fichier dans le dossier upload
                $image->move(
                    $this->getParameter('upload_directory'),
                    $fichier
                );

                // on stocke l'image dans la bdd (son nom)
                $Utilisateur->setUtilisateurpdp($fichier);

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

                //recuperation des images transmises
                $image = $form->get('utilisateurpdp')->getData();
                // Boucle sur les images

                // on genere un nouveau nom de fichier avec md5. guessExtension recupere l'extension du fichier
                $fichier = md5(uniqid()) . '.' . $image->guessExtension();
                //On copie le fichier dans le dossier upload
                $image->move(
                    $this->getParameter('upload_directory'),
                    $fichier
                );

                // on stocke l'image dans la bdd (son nom)
                $Utilisateur->setUtilisateurpdp($fichier);

                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("acceuil");
            }
            return $this->render("utilisateur/updateEntrepreneur.html.twig", array("formulaire" => $form->createView()));
        }else  {
            $form = $this->createForm(AdminType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {

                //recuperation des images transmises
                $image = $form->get('utilisateurpdp')->getData();
                // Boucle sur les images

                // on genere un nouveau nom de fichier avec md5. guessExtension recupere l'extension du fichier
                $fichier = md5(uniqid()) . '.' . $image->guessExtension();
                //On copie le fichier dans le dossier upload
                $image->move(
                    $this->getParameter('upload_directory'),
                    $fichier
                );

                // on stocke l'image dans la bdd (son nom)
                $Utilisateur->setUtilisateurpdp($fichier);

                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("acceuil");
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
            return $this->render("acceuil/AcceuilConnectéAdmin.html.twig");
        }
        else if(strcmp($role,"Entrepreneur")==0){
            return $this->render("acceuil/AcceuilConnectéEntrepreuneur.html.twig");


        }
        else if(strcmp($role,"Formateur")==0){
            return $this->render("acceuil/AcceuilConnectéAdmin.html.twig");


        }
            else{
            return $this->render("acceuil/AcceuilConnecté.html.twig",array('session'=>$_SESSION));
        }

        //return $this->redirectToRoute("utilisateur");
        // return $this->render("utilisateur/acceuilAdmin.html.twig");

    }

    /**
     * @Route("/verify", name="verify")
     * @param Request $request
     * @param UserPasswordEncoderInterface $passwordEncoder
     * @return Response
     * @throws \Twilio\Exceptions\ConfigurationException
     * @throws \Twilio\Exceptions\TwilioException
     */
    public function verifyCode(Request $request, UserPasswordEncoderInterface $passwordEncoder): Response
    {

        $num = $request->request->get('utilisateurphone');
        $n = '+216' . $num;

        $Utilisateur = new Utilisateur ();
        $form = $this->createForm(MotDePasseOublieType::class, $Utilisateur);
        $form->handleRequest($request);
        if($form->isSubmitted()){

            $account_sid = 'AC04fedb666177e902b410a42d0b4614b9';
            $auth_token = 'e19969ecb5f0279d8539e03f2c414f40';
            $client = new Client($account_sid, $auth_token);

            $verification = $client->verify->v2->services("VA43d502871f086dd1dc62cb5fccfef0b2")
                ->verifications
                ->create($n, "sms");

            print($verification->status);

            $user_params = [
                'utilisateurphone' => $request->request->get('utilisateurphone'),
                'utilisateurmdp' => $request->request->get('utilisateurmdp'),

            ];

            $this->get('session')->set('user', $user_params);
            return $this->render("utilisateur/verify.html.twig");

        }

        return $this->render("utilisateur/MotDePasseoublie.html.twig", array("formulaire" => $form->createView()));
    }
    public function verfiy($n,$c){
        $sid = "AC04fedb666177e902b410a42d0b4614b9";
        $token = "e19969ecb5f0279d8539e03f2c414f40";
        $twilio = new Client($sid, $token);

        try {
            $verification_check = $twilio->verify->v2->services("VA43d502871f086dd1dc62cb5fccfef0b2")
                ->verificationChecks
                ->create($c, // code
                    ["to" => $n]
                );
        } catch (TwilioException $e) {
        }

        $status=$verification_check->status;
        return $status;}


    /**
     * @Route("/searchUtilisateur ", name="searchUtilisateur")
     */
    public function searchUtilisateur(Request $request,NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Utilisateur::class);
        $requestString=$request->get('searchValue');
        $utilisateur = $repository->findByutilisateuradresseemail($requestString);
        $jsonContent = $Normalizer->normalize($utilisateur, 'json',['groups'=>'utilisateurs']);
        $retour=json_encode($jsonContent);
        return new Response($retour);

    }








}
