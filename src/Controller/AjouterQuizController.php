<?php

namespace App\Controller;

use App\Entity\Questionn;
use App\Repository\CoursRepository;
use App\Repository\QuestionnRepository;
use App\Services\QrcodeService;
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
        return $this->render('ajouter_quiz/show.html.twig', [
            'controller_name' => 'AjouterQuizController',
        ]);
    }

    /**
     * @Route("/passer_quiz" , name="passer_quiz")
     */

    public function liste_quiz(Request $request,CoursRepository $repository,QuestionnRepository $repo)
    {
 $question=  $repo->findAll();
$cours = $repository->findAll();


        return $this->render("quizz/index_front.html.twig", [
            'cours' => $cours,
            'question' => $question,

        ]);
    }
    /**
     * @Route("/question" , name="question")
     */

    public function question(QrcodeService $qrcodeService,Request $request,QuestionnRepository $repository,CoursRepository $coursRepository,\Swift_Mailer $mailer)
    {

       //$qts = $repository->findBy(['cours_id' => $request->request->get('cours')]);
        $qts = $this->getDoctrine()->getRepository(Questionn::class)->findBy(['cours_id' => $request->request->get('quiz')]);
$name= $repository->findOneBy(['cours_id' => $request->request->get('quiz')]);

        $cours= $repository->findAll();


        $new=$qts;
        $qrCode = null;
        $qrCode = $qrcodeService->qrcode("Bien débuter avec Spring");
        //////////////
       $message = (new \Swift_Message('Felicitation '))
            ///expediteur
            ->setFrom('fares.moalla1996@gmail.com')

            ///destinataire
            ->setTo('9ariniphoenix@gmail.com')
            //message avec vue twig
            ->setBody(
                $this->renderView(
                    'etudiant/certif2.html.twig',compact('new')
                ),
                'text/html'
            ) ;


        $mailer->send($message);

        $html2 = $this->renderView('etudiant/certif.html.twig', [
            'qrCode2' => $qrCode,
            'questions'=>$qts,
        ]);



        return $this->render("quizz/quiz.html.twig", [
            'questions' => $qts,
            'i' => 1,
            'n' => count($qts),
            'cours'=>$cours,
            'name'=>$name,

            'cours_id' => $request->request->get('quiz')
        ]);



    }

    /**
     * @Route("/res/{id}" , name="res")
     */
    public function res($id , Request $request,QuestionnRepository $repository){
        $res=$request->request->get('res');
        $qts = $repository->findBy(['cours_id' =>$id]);
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
