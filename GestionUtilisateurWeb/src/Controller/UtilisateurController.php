<?php

namespace App\Controller;


use App\Entity\Utilisateur;
use App\Form\UtilisateurType;
use App\Repository\UtilisateurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


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
     */
    public function ajouterUtilisateur(Request $request): Response
    {
        $Utilisateur = new Utilisateur ();
        $form= $this->createForm(UtilisateurType::class,$Utilisateur);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $imageFile = $form->get('utilisateurpdp')->getData();
            $mdp=$form->get('utilisateurmdp')->getData();
            $hashmdp=sha1($mdp);

            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'C:\wamp64\www\image',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $Utilisateur->setUtilisateurpdp($newFilename);
                $Utilisateur->setUtilisateurmdp($hashmdp);
            }
            $em= $this->getDoctrine()->getManager();
            $em->persist($Utilisateur );
            $em->flush();
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute("utilisateur");
        }
        return $this->render("utilisateur/ajouterUtilisateur.html.twig",array("formulaire"=>$form->createView()));
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
        $form= $this->createForm(UtilisateurType::class,$Utilisateur);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("utilisateur");
        }
        return $this->render("utilisateur/update.html.twig",array("formulaire"=>$form->createView()));
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



}
