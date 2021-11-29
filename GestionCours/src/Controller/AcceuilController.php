<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AcceuilController extends AbstractController
{
    /**
     * @Route("/acceuil", name="acceuil")
     */
    public function index(): Response
    {
        return $this->render('acceuil/Acceuil.html.twig', [
            'controller_name' => 'AcceuilController',
        ]);
    }

    /**
     * @Route("/acceuil2", name="acceuil2")
     */
    public function index2(): Response
    {
        return $this->render('acceuil/Acceuil2.html.twig', [
            'controller_name2' => 'AcceuilController2',
        ]);
    }



    /**
         * @Route("/firstpage", name="firstpage")
     */
    public function firstpage(){
        return $this->render('/cours/test.html.twig', [
            'firstpage' => 'firstpage',
        ]);
    }






}
