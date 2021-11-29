<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Cours;
use App\Entity\Formation;
use App\Form\CourssearchType;
use App\Form\CoursType;
use App\Form\RechCoursType;
use App\Repository\CategorieRepository;
use App\Repository\CoursRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\File\Stream;
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




    ///////////////////////////////////////////////////////////////

    /**
     * @Route("/affichercours",name="affichercours")
     */
    public function AfficheavecRech(Request $request){


        //////////////////
        $cours =$this->getDoctrine()->getRepository(Cours::class)->listCoursByNsc();

        $form = $this->createForm(RechCoursType::class);
        $form->handleRequest($request);

        if($form->isSubmitted()){
            $nomcours=$form->getData()->getNomcours();
            $coursResult=$this->getDoctrine()->getRepository(Cours::class)->getCoursByNom($nomcours);





            return $this->render('cours/tablecours.html.twig', [
                'courss2' => $coursResult,
                'form2' => $form->createView()
            ]);
        }
        return $this->render('cours/tablecours.html.twig', [
            'courss2' => $cours,
            'form2' => $form->createView()
        ]);
    }




    /////////////////////////////////////////////////////////////////////
    /**
     * @Route("/AfficheCours",name="AfficheCours")
     */
    public function Affiche(CoursRepository $repository){
//$repo=$this->getDoctrine()->getRepository(Classroom::class);
        $tableCours=$repository->findAll();
        return $this->render('cours/tablecours.html.twig'
            ,['tablecours'=>$tableCours]);

    }


    ///////////////////////////////////////////

    /**
     * @Route("/supprimercours/{id}",name="supprimercours")
     */
    public function delete($id,EntityManagerInterface $em ,CoursRepository $repository){
        $cours=$repository->find($id);
        $em->remove($cours);
        $em->flush();
        $this->addFlash(
            'info','Supprimé avec succées '
        );
       // $flashy->success('Event created!', 'http://your-awesome-link.com');
        return $this->redirectToRoute('affichercours');
    }

    /**
     * @Route("/ajoutcours",name="ajoutcours")
     */
    public function addCours(EntityManagerInterface $em,Request $request,\Swift_Mailer $mailer  /*,FlashyNotifier $flashy */){
        $cours= new Cours();
        $form= $this->createForm(CoursType::class,$cours);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
     //  $id = new Categorie();

        if($form->isSubmitted() && $form->isValid()){
            $new=$form->getData();
            $imageFile = $form->get('coursimg')->getData();
            if ($imageFile) {
                $originalFilename = pathinfo($imageFile->getClientOriginalName(), PATHINFO_FILENAME);
                $newFilename = $originalFilename.'-'.uniqid().'.'.$imageFile->guessExtension();
                try {
                    $imageFile->move(
                        'front\web\images',
                        $newFilename
                    );
                } catch (FileException $e) {
                }
                $cours->setCoursimg($newFilename);
            }
            $em->persist($cours);
            $em->flush();
            $this->addFlash(
                'info','Added successfully!');

  ////////Hedhi mail/////
           $message = (new \Swift_Message('Vous avez ajouté un nouveau cours'))
                ///expediteur
                ->setFrom('fares.moalla1996@gmail.com')

                ///destinataire
                ->setTo('9ariniphoenix@gmail.com')
                //message avec vue twig
                ->setBody(
                    $this->renderView(
                        'email/contact.html.twig',compact('new')
                    ),
                    'text/html'
                ) ;
            $mailer->send($message);
            // $flashy->success('Event created!', 'http://your-awesome-link.com');

            return $this->redirectToRoute("affichercours");
        }
        return $this->render("cours/ajoutCours.html.twig",array("form"=>$form->createView()));
    }



    /**
     * @Route("/{id}/edit", name="modifiercours", methods={"GET","POST"})
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
                        'front\web\images',
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

            return $this->redirectToRoute('affichercours');
        }

        return $this->render('cours/Modifiercours.html.twig', [
            'coursmodif' => $cours,
            'form' => $form->createView(),
        ]);
    }


/////////////////
    /**
     * @Route("/stat", name="stat")
     */
    public function statAction(CategorieRepository $coursRepository)
    {
$categoriee= $coursRepository->findAll();
$categorie= [];
$coursCount= [];
foreach($categoriee as $categorie ){
    $categorienom[]=$categorie->getCategorienom();
    $categorieCount[]= count($categorie->getCours());
}

        return $this->render('cours/base.html.twig',
            [
                'categorieNom' => json_encode($categorienom),
                'categorieCount' => json_encode($categorieCount), 'base2' => 'base2'
            ]);


    }


    /**
     * @Route("/pdf/{id}", name="pdf" ,  methods={"GET"})
     */
public function pdf($id,CoursRepository $repository){
   ///////////
     $cours=$repository->find($id);

    /*return $this->render('cours/pdf.html.twig'
        ,['tablecours'=>$cours]);*/
    // Configure Dompdf according to your needs




    $pdfOptions = new Options();
    $pdfOptions->set('defaultFont', 'Arial');

    // Instantiate Dompdf with our options
    $dompdf = new Dompdf($pdfOptions);

    // Retrieve the HTML generated in our twig file
    $html = $this->renderView('cours/pdf.html.twig', [
        'pdf' => $cours
    ]);
/*
 // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Store PDF Binary Data
        $output = $dompdf->output();

        // In this case, we want to write the file in the public directory
        $publicDirectory = $this->get('kernel')->getProjectDir() . '/public';
        // e.g /var/www/project/public/mypdf.pdf
        $pdfFilepath =  $publicDirectory . '/mypdf.pdf';

        // Write file to the desired path
        file_put_contents($pdfFilepath, $output);

        // Send some text response
        return new Response("The PDF file has been succesfully generated !");

 */
    // Load HTML to Dompdf
    $dompdf->loadHtml($html);

    // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
    $dompdf->setPaper('A4', 'portrait');

    // Render the HTML as PDF
    $dompdf->render();

    // Output the generated PDF to Browser (force download)
    $dompdf->stream($cours->getNomcours(), [
        "Attachment" => true
    ]);
}

    /**
     * @Route("/showw/cours/{categorieid}",name="showcours")
     */

    public function getCourss(EntityManagerInterface $em , Request $request , CategorieRepository  $repository)
    {

        $cours = new cours();
        $categorie = $repository->findOneBy(['id' => $request->get('categorie_id')]);

        $cours->setCategorie($categorie) ;
        $cours->setNomcours($request->get('nomcours'));
        $cours->setCoursimg($request->get('coursimg'));
        $cours->setDescription($request->get('description'));
        $cours->setNbrchapitres($request->get('nbrchapitres'));
        $cours->setUtilisateurnom($request->get('utilisateurnom'));



        $em->persist($cours);
        $em->flush();

        return $this->redirectToRoute('get_categorie', ['id' =>$request->get('categorie_id')]);

    }








}
