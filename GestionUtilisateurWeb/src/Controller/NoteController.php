<?php

namespace App\Controller;

use App\Entity\Note;
use App\Entity\Utilisateur;
use App\Form\NoteType;
use App\Form\NoteUtilisateurType;
use App\Repository\NoteRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class NoteController extends AbstractController
{
    /**
     * @Route("/note", name="note")
     */
    public function index(): Response
    {
        return $this->render('note/index.html.twig', [
            'controller_name' => 'NoteController',
        ]);
    }
    /**
     * @Route("/ajoutNote/{id}", name="ajoutNote")
     */
    public function ajoutNote(Request $request,$id): Response
    {
        $Note = new Note();

        $Utilisateur = $this->getDoctrine()->getRepository(Utilisateur::class)->find($id);
        $Note->setUtilisateur($Utilisateur);
        $form= $this->createForm(NoteType::class,$Note);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($Note );
            $em->flush();
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute("note");
        }
        return $this->render("note/ajoutNote.html.twig",array("formulaire"=>$form->createView()));
    }
    /**
     * @Route("/modifierNote/{id}",name="modifierNote")
     */
    public function update(Request $request,$id)
    {

        $Note = $this->getDoctrine()->getRepository(Note::class)->find($id);


        $form= $this->createForm(NoteType::class,$Note);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("note");
        }
        return $this->render("note/modifierNote.html.twig",array("formulaire"=>$form->createView()));
    }
    /**
     * @Route("/supprimerNote/{id}",name="supprimerNote")
     */
    public function delete($id)
    {
        $Note= $this->getDoctrine()->getRepository(Note::class)
            ->find($id);
        $em= $this->getDoctrine()->getManager();
        $em->remove($Note);
        $em->flush();
        return $this->redirectToRoute("note");
    }
}
