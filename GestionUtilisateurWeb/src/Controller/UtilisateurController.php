<?php

namespace App\Controller;


use App\Entity\Utilisateur;
use App\Form\EntrepreneurType;
use App\Form\FormateurType;
use App\Form\InscriptionType;
use App\Form\LoginType;
use App\Form\MembreType;
use App\Form\UtilisateurType;
use App\Repository\UtilisateurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use function PHPUnit\Framework\equalTo;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\PasswordHasher\Hasher\UserPasswordHasherInterface;


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
            return $this->redirectToRoute("utilisateur");
        }
        return $this->render("utilisateur/Inscription.html.twig",array("formulaire"=>$form->createView()));
    }
    /**
     * @Route("/supprimerUtilisateur/{id}",name="supprimerUtilisateur")
     */
    public function delete($id)
    {
        $club= $this->getDoctrine()->getRepository(Utilisateur::class)
            ->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($club);
        $em->flush();
        return $this->redirectToRoute("utilisateur");
    }
    /**
     * @Route("/modifierUtilisateur/{id}",name="modifierUtilisateur")
     */
    public function update(Request $request,$id)
    {
        $Utilisateur = $this->getDoctrine()->getRepository(Utilisateur::class)->find($id);
        $Utilisateur->setUtilisateurmdp("");
        if(strcmp($Utilisateur->getUtilisateurrole(),"Membre")==0) {
            $form = $this->createForm(MembreType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("utilisateur");
            }
            return $this->render("utilisateur/updateMembre.html.twig", array("formulaire" => $form->createView()));
        }
        else if(strcmp($Utilisateur->getUtilisateurrole(),"Formateur")==0) {
            $form = $this->createForm(FormateurType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("utilisateur");
            }
            return $this->render("utilisateur/updateFormateur.html.twig", array("formulaire" => $form->createView()));
        }
        else if(strcmp($Utilisateur->getUtilisateurrole(),"Entrepreneur")==0) {
            $form = $this->createForm(EntrepreneurType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("utilisateur");
            }
            return $this->render("utilisateur/updateEntrepreneur.html.twig", array("formulaire" => $form->createView()));
        }else  {
            $form = $this->createForm(UtilisateurType::class, $Utilisateur);
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $em->flush();
                return $this->redirectToRoute("utilisateur");
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

            return $this->render("utilisateur/acceuil.html.twig");
        }



}
