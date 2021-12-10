<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Entity\Cours;
use App\Entity\Formation;
use App\Entity\Utilisateur;
use App\Form\CourssearchType;
use App\Form\CoursType;
use App\Form\RechCoursType;
use App\Repository\CategorieRepository;
use App\Repository\CibleRepository;
use App\Repository\CoursRepository;
use App\Repository\NoteRepository;
use App\Repository\PubliciteRepository;
use App\Repository\UserRepository;
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
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;


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
    public function delete(FlashyNotifier $flashy,$id,EntityManagerInterface $em ,CoursRepository $repository,UserRepository $UserRepository,\Swift_Mailer $mailer){
        $cours=$repository->find($id);
        $em->remove($cours);
        $em->flush();
        $new=$em;
        $this->addFlash(
            'info','Supprimé avec succées '
        );
        $email= $UserRepository->findAll();
        $mail=[];

        foreach($email as $mail){
            $listeemail[]=$mail->getEmail();
            $msg= $cours->getNomcours();
            $message = (new \Swift_Message('le cours  '.$msg.' '.' nest plus disponible  :'))
                ->setFrom('fares.moalla1996@gmail.com')
                ->setTo($listeemail)
                //message avec vue twig
                ->setBody(
                    $this->renderView(
                        'email/contact.html.twig',compact('new')
                    ),
                    'text/html'
                ) ;
        }
        $mailer->send($message);



        $msgg= $cours->getNomcours();
        $flashy->success('Cours'.$msgg. 'supprimé avec succés ');
        return $this->redirectToRoute('affichercours');
    }

    /**
     * @Route("/ajoutcours",name="ajoutcours")
     */
    public function addCours(EntityManagerInterface $em,Request $request,\Swift_Mailer $mailer  ,FlashyNotifier $flashy ,UserRepository $UserRepository){
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

                ///expediteur

$email= $UserRepository->findAll();
           $mail=[];
foreach($email as $mail){
    $listeemail[]=$mail->getEmail();
    $msg= $cours->getNomcours();

    $message = (new \Swift_Message("Un nouveau Cours a été ajouté  ".$msg))

    ->setFrom('fares.moalla1996@gmail.com')
    ->setTo($listeemail)
        //message avec vue twig
        ->setBody(
            $this->renderView(
                'email/contact.html.twig',compact('new')
            ),
            'text/html'
        ) ;
}
            $mailer->send($message);

            $msgcours= $cours->getNomcours();
            $flashy->success('Cours ajouté avec succés'.$msgcours);

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

/// ////////////////
    /**
     * @Route("/stat", name="stat")
     */
    public function statAction(CibleRepository $cibleRepository ,PubliciteRepository $publiciteRepository,CategorieRepository $coursRepository,CoursRepository $test,NoteRepository $repo,UtilisateurController $uti)
    {
$categoriee= $coursRepository->findAll();



$coursss= $test->findAll();
$nbrCours=[];
foreach($coursss as $cours){
    $coursnom[]=$cours->getNomcours();
$coursprix[]=$cours->getNbrchapitres();
}

////////

///
$categorie= [];
$coursCount= [];
foreach($categoriee as $categorie ){
    $categorienom[]=$categorie->getCategorienom();
    $categorieCount[]= count($categorie->getCours());
}
        $notes= $repo->findAll();
        foreach($notes as $n ){
            $utilisateurid=$n->getUtilisateur()->getId();

            $moy=0;
            $nb=0;
            $s=0;
            foreach($notes as $n){

                if($n->getUtilisateur()->getId()==$utilisateurid){

                    $nb++;

                    $note=$n->getNotevaleur();
                    $s=($s+$note);



                }

            }
            $moy=$s/$nb;

            $u=  $n->getUtilisateur();
            $u->setNote($moy);
            $em= $uti->getDoctrine()->getManager();

            $em->persist($u);
            $em->flush();

        }
        $utilisateurss=$uti->getDoctrine()->getRepository(Utilisateur::class)
            ->findAll();
        $final[$moy]=array();
        foreach($utilisateurss as $utilisateurs ){
            $final[$moy][]=$utilisateurs->getNote();
            $utilisateurcount[]= $utilisateurs->getUtilisateuradresseemail();
        }
        $ciblee= $cibleRepository->findAll();
        $cible= [];

        foreach($ciblee as $cible ){
            $cibleId[]=$cible->getId();
            $publicitesCount[]= count($cible->getPublicites());
        }

        $pubs= $publiciteRepository->findAll();
        $pub= [];

        foreach($pubs as $pub ){
            $nbrclick[]=$pub->getNbrClick();
            $pubid[]=$pub->getId() ;
            $nbrAffichage[]=$pub->getNbrAffichage();
        }

        return $this->render('cours/base.html.twig',
            [
                'categorieNom' => json_encode($categorienom),
                'coursnom'=> json_encode($coursnom),
                'cibleID' => json_encode($cibleId),
                'publicitesCount' => json_encode($publicitesCount),
                'PubliciteID' => json_encode($pubid),
                'nbrClick' => json_encode($nbrclick),
                'nbrAffichage'=>json_encode($nbrAffichage),
                'coursprix'=> json_encode($coursprix),
                'categorieCount' => json_encode($categorieCount), 'base2' => 'base2',
                'note' => json_encode($final[$moy]),
                'utilisateurcount' => json_encode($utilisateurcount), 'base3' => 'base3'
            ]);


    }





    /**
     * @Route("/pdf/{id}", name="pdf" ,  methods={"GET"})
     */
public function pdf($id,CoursRepository $repository){
    /*return $this->render('cours/pdf.html.twig'
        ,['tablecours'=>$cours]);*/
    // Configure Dompdf according to your needs
//https://artisansweb.net/how-to-convert-html-to-pdf-in-php/
    //$dompdf->loadHtmlFile('web\images\hello.pdf');
    /* $file = "admin/store/orders/45/invoice/print";
     $dompdf->load_html_file('http://yourdomain.ext/'.$file);
       $dompdf->set_option('chroot', getcwd()); //assuming HTML file is in the root folder
   $dompdf->loadHtmlFile('front/web/images/hello.html');
  $output = $dompdf->output();
   file_put_contents("front/web/images/hello.pdf", $output);

     $dompdf->set_option('enable_remote', TRUE);

     $dompdf->set_option('enable_css_float', TRUE);
     $dompdf->set_option('enable_html5_parser', FALSE);
 */
   $cours=$repository->find($id);

    $pdfOptions = new Options();
    $pdfOptions->set('defaultFont', 'Arial');
    $dompdf = new Dompdf($pdfOptions);
    $html = $this->renderView('cours/pdf.html.twig', [
        'pdf' => $cours
    ]);
    $dompdf->loadHtml($html);

    // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
    $dompdf->setPaper('A4', 'portrait');

    // Render the HTML as PDF
    $dompdf->render();
  //  $dompdf->stream();
    // Output the generated PDF to Browser (force download)
    $dompdf->stream($cours->getNomcours(), [
        "Attachment" => false
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



    /**
     * @Route("/searchcoursss ", name="searchcoursss")
     */
    public function searchcoursss(Request $request,NormalizerInterface $Normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Cours::class);
        $requestString=$request->get('searchValue');
        $cours = $repository->searchcccc($requestString);
        $jsonContent = $Normalizer->normalize($cours, 'json',['groups'=>'cours']);
        $retour=json_encode($jsonContent);
        return new Response($retour);

    }










}
