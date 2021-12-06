<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Form\RechCoursType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AcceuilController extends AbstractController
{


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
     * @Route("/acceuilconnecte", name="acceuilconnecte")
     */
    public function index3(): Response
    {
        return $this->render('acceuil/AcceuilConnecté.html.twig', [
            'controller_name3' => 'AcceuilController3',
        ]);
    }



}
