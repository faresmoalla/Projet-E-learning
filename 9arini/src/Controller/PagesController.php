<?php

namespace App\Controller;

use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;

use Symfony\Component\Routing\Annotation\Route;

class PagesController extends AbstractController
{
    /**
     * @Route("/", name="home")
     */
    public function home(FlashyNotifier $flashy): Response
    {
        $flashy->success('Event created!', 'http://your-awesome-link.com');

        return $this->redirectToRoute('about');

    }


    /**
     * @Route("/about",name="about")
     */

    public function about(){
        return $this->render('pages/about.html.twig');


    }
}
