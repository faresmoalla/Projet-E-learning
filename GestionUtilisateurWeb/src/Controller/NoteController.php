<?php

namespace App\Controller;

use App\Entity\Note;
use App\Entity\Utilisateur;
use App\Form\NoteType;
use App\Form\NoteUtilisateurType;
use App\Repository\NoteRepository;
use App\Repository\UtilisateurRepository;
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
    /**
     * @Route("/moyennenote",name="moyennenote")
     */
    public function moyenne(NoteRepository $repo,UtilisateurController $uti)
    {   /*
        $Note= $this->getDoctrine()->getRepository(Note::class)->findAll();
        foreach ($Note as $n){
            $utilisateur=$n->getUtilisateur()->getId();
            $note=$n->getNotevaleur();
            $nb=0;
            while($n->getUtilisateur()->getId()==$utilisateur){
                $nb++;
                $note+=($note+$n->getNotevaleur())/$nb;
                $n->getUtilisateur()->setNote($note);
            }
        }

        return $this->render("note/dashboardnote.html.twig");*/
        $notes= $repo->findAll();
        foreach($notes as $n ){
            $utilisateurid=$n->getUtilisateur()->getId();

            $moy=0;
            $nb=0;
            $s=0;
            foreach($notes as $n){

                if($n->getUtilisateur()->getId()==$utilisateurid){

                    $nb++;

                    $note=$n->getNotevaleur();
                    $s=($s+$note);



                }

            }
            $moy=$s/$nb;

            $u=  $n->getUtilisateur();
            $u->setNote($moy);
            $em= $uti->getDoctrine()->getManager();

            $em->persist($u);
            $em->flush();

        }
        $utilisateurss=$uti->getDoctrine()->getRepository(Utilisateur::class)
            ->findAll();
        $final[$moy]=array();
        foreach($utilisateurss as $utilisateurs ){
            $final[$moy][]=$utilisateurs->getNote();
            $utilisateurcount[]= $utilisateurs->getUtilisateuradresseemail();
        }


        return $this->render('note/moyennenote.html.twig',
            [
                'note' => json_encode($final[$moy]),
                'utilisateurcount' => json_encode($utilisateurcount), 'base2' => 'base2'
            ]);

    }


    /**
     * @Route("/stat", name="stat")
     */
    public function statAction(NoteRepository $repo)
    {
        $utilisateurss= $repo->findAll();
        $utilisateurs= [];
        foreach($utilisateurss as $utilisateurs ){
            $note[]=$utilisateurs->getNotevaleur();
            $utilisateurcount[]= $utilisateurs->getUtilisateur()->getId();
        }

        return $this->render('note/dashboardnote.html.twig',
            [
                'note' => json_encode($note),
                'utilisateurcount' => json_encode($utilisateurcount), 'base2' => 'base2'
            ]);


    }

}
