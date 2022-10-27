<?php

namespace App\Controller;

use App\Entity\Cible;
use App\Entity\Tarif;
use App\Form\TarifType;
use phpDocumentor\Reflection\DocBlock\Tags\TagWithType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TarifController extends AbstractController
{
    /**
     * @Route("/Afiichetarif", name="tarif")
     */
    public function index(Request $request): Response
    {
        $tarif=$this->getDoctrine()->getRepository(tarif::class)->findAll();
        return $this->render('tarif/Affichertarif.html.twig', [
            'tarif'=>$tarif,
        ]);
    }
    /**
     * @Route("/updateTarif/{id}",name="updateTarif")
     */
    public function update(Request $request,$id)
    {
        $tarif=$this->getDoctrine()->getRepository(Tarif::class)->find($id);
        $form= $this->createForm(TarifType::class,$tarif);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );
            return $this->redirectToRoute("tarif");
        }
        return $this->render("tarif/updatetarif.html.twig",array("form"=>$form->createView()));
    }

}
