<?php

namespace App\Controller;

use App\Entity\Questionn;
use App\Repository\CoursRepository;
use App\Repository\QuestionnRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AjouterQuizController extends AbstractController
{
    /**
     * @Route("/ajouter/quiz", name="ajouter_quiz")
     */
    public function index(): Response
    {
        return $this->render('ajouter_quiz/index.html.twig', [
            'controller_name' => 'AjouterQuizController',
        ]);
    }

    /**
     * @Route("/passer_quiz" , name="passer_quiz")
     */

    public function liste_quiz(Request $request,CoursRepository $repository)
    {

$cours = $repository->findAll();

        return $this->render("quizz/index_front.html.twig", [
            'cours' => $cours,
        ]);
    }
    /**
     * @Route("/question" , name="question")
     */

    public function question(Request $request,QuestionnRepository $repository,CoursRepository $coursRepository,\Swift_Mailer $mailer)
    {

        $qts = $repository->findBy(['idquiz' => $request->request->get('quiz')]);
$cours= $coursRepository->findAll();

        $new=$qts;

        //////////////
        $message = (new \Swift_Message('Felicitation '))
            ///expediteur
            ->setFrom('fares.moalla1996@gmail.com')

            ///destinataire
            ->setTo('9ariniphoenix@gmail.com')
            //message avec vue twig
            ->setBody(
                $this->renderView(
                    'etudiant/certif.html.twig',compact('new')
                ),
                'text/html'
            ) ;
        $mailer->send($message);





        return $this->render("quizz/quiz.html.twig", [
            'questions' => $qts,
            'i' => 1,
            'n' => count($qts),

            'idquiz' => $request->request->get('quiz')
        ]);



    }

    /**
     * @Route("/res/{id}" , name="res")
     */
    public function res($id , Request $request,QuestionnRepository $repository){
        $res=$request->request->get('res');
        $qts = $repository->findBy(['idquiz' =>$id]);
        $this->render("quizz/resultat.html.twig",[
            'res' => $res
        ]);
        $qt= count($qts);
        $p=$res/count($qts)* 100;
        return $this->render("quizz/resultat.html.twig",[
            'res' => $res/count($qts)* 100
        ]);



    }






}
