<?php

namespace App\Controller;
use App\Repository\ChapitreRepository;
use App\Form\ChapitreType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use App\Entity\Chapitre;


class ChapitreController extends AbstractController
{
    /**
     * @Route("/chapitre", name="chapitre")
     */
    public function index(): Response
    {
        return $this->render('chapitre/index.html.twig', [
            'controller_name' => 'ChapitreController',
        ]);
    }

    /**

     * @Route("/ajoutchapitre",name="ajoutchapitre")
     */
    public function add(EntityManagerInterface $em,Request $request){
        $chapitre= new Chapitre();

        $form= $this->createForm(ChapitreType::class,$chapitre);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){

            $em->persist($chapitre);
            $em->flush();
            $this->addFlash(
                'info','Added successfully!'
            );
            // return $this->redirectToRoute("AfficheChapitre");

        }
        return $this->render("chapitre/ajoutChapitre.html.twig",array("formchapitre"=>$form->createView()));
    }



}
