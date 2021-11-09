<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Form\CourssearchType;
use App\Form\CoursType;
use App\Repository\CoursRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Knp\Component\Pager\PaginatorInterface;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
class CoursController extends AbstractController
{
    /**
     * @Route("/cours", name="cours")
     */
    public function index(): Response
    {
        return $this->render('cours/index.html.twig', [
            'controller_name' => 'CoursController',
        ]);
    }
    /**
     * @Route("/AfficheCours",name="AfficheCours")
     */
    public function Affiche(CoursRepository $repository){
//$repo=$this->getDoctrine()->getRepository(Classroom::class);
        $tableCours=$repository->findAll();
        return $this->render('cours/tablecours.html.twig'
            ,['tablecours'=>$tableCours]);

    }

    /**
     * @Route("/supprimercours/{coursID}",name="supprimercours")
     */
    public function delete($coursID,EntityManagerInterface $em ,CoursRepository $repository){
        $cours=$repository->find($coursID);
        $em->remove($cours);
        $em->flush();
        return $this->redirectToRoute('AfficheCours');
    }

    /**
     * @Route("/ajoutcours",name="ajoutcours")
     */
    public function addCours(EntityManagerInterface $em,Request $request){
        $cours= new Cours();

        $form= $this->createForm(CoursType::class,$cours);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $imageFile = $form->get('coursimg')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'C:\wamp64\www\GestionCours\public\front\web\images',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $cours->setCoursimg($newFilename);
            }
            $em->persist($cours);
            $em->flush();
            //$request->getSession()->getFlashBag()->add();
            $this->addFlash(
                'info','Added successfully!'
            );



            return $this->redirectToRoute("AfficheCours");

        }
        return $this->render("cours/ajoutCours.html.twig",array("form"=>$form->createView()));
    }



    /**
     * @Route("/{coursid}/edit", name="modifiercours", methods={"GET","POST"})
     */
    public function edit(Request $request, Cours $cours): Response
    {
        $form = $this->createForm(CoursType::class, $cours);
        $form->add('Confirmer',SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('coursimg')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'C:\wamp64\www\GestionCours\public\front\web\images',
                        $newFilename
                    );
                } catch (FileException $e) {
                    // ... handle exception if something happens during file upload
                }
                $cours->setCoursimg($newFilename);
            }
            $this->getDoctrine()->getManager()->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );

            return $this->redirectToRoute('AfficheCours');
        }

        return $this->render('cours/Modifiercours.html.twig', [
            'coursmodif' => $cours,
            'form' => $form->createView(),
        ]);
    }


/////////////////
    /**
     * @Route("/StatCours", name="StatCours")
     */
    public function statAction()//Essaie d'ajouter même plus de Pie Charts^^
    {

        $pieChart = new PieChart();

        $number1 = $this->getDoctrine()
            ->getRepository(Cours::class)
            ->numberOfUsersGenre1();
        $number2 = $this->getDoctrine()
            ->getRepository(Cours::class)
            ->numberOfUsersGenre2();
        $a=5-$number1;
        $b=5-$number2;



        $pieChart->getData()->setArrayToDataTable(
            [['Cours', 'Pie Partie'],
                ['Base de donnée',5-$a],
                ['Web',5-$b],
            ]
        );

        $pieChart->getOptions()->setTitle('Cours selon les catégories');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(500);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#07600');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(25);


        return $this->render('cours/stat.html.twig', array(
                'piechart' => $pieChart,
            )

        );
    }
    /// ///////////

























    /**
     * @Route("/ClientIndex", name="Client_index", methods={"GET"})
     */
    public function ClientIndex(CoursRepository $repository): Response
    {
        $cours = $repository->findAll();

     /*   return $this->render('cours/show.html.twig', [
            'cours' => $cours,
        ]);*/
    }






















}
