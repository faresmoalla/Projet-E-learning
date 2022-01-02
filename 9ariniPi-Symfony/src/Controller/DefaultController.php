<?php

namespace App\Controller;

use App\Form\SearchType;
use App\Services\QrcodeService;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class DefaultController extends AbstractController
{
    /**
     * @Route("/qrcode", name="homepage")
     * @param Request $request
     * @param QrcodeService $qrcodeService
     * @return Response
     */
    public function index(Request $request, QrcodeService $qrcodeService): Response
    {

         $qrCode = null;
        $qrCode = $qrcodeService->qrcode('hello');

        return $this->render('default/show.html.twig', [
            'qrCode' => $qrCode
        ]);


/*
        $qrCode = null;
        $form = $this->createForm(SearchType::class, null);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $data = $form->getData();
            $qrCode = $qrcodeService->qrcode($data['name']);
        }

        return $this->render('default/show.html.twig', [
            'form' => $form->createView(),
            'qrCode' => $qrCode
        ]);*/
    }
}