<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\CommentsType;
use App\Repository\ReclamationRepository;
use App\Form\ReclamationType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ReclamationController extends AbstractController
{

    /**
     * @Route("addreclamation", name="addreclamation")
     */


    function addReclamation(Request $request ){
        $reclamation = new Reclamation();
        $form=$this->createForm(ReclamationType::class , $reclamation);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        ;
        if($form->isSubmitted() && $form->isValid()){



            $reclamation->setCreatedAt(new \DateTimeImmutable());


            $em=$this->getDoctrine()->getManager();
            $em->persist($reclamation);
            $em->flush();
        }
        return $this->render('reclamation/addreclamation.html.twig',[
            'form'=>$form->createView()
        ]);

    }
    /**
     * @Route("/afficheReclamation",name="affichereclamation")
     */
    public function getAllReclamation (ReclamationRepository $repository){
        $tablereclamation=$repository->findAll();
        return $this->render('reclamation/affichereclamation.html.twig'
            ,['tableReclamation'=>$tablereclamation]);

    }


}
