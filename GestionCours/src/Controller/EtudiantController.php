<?php

namespace App\Controller;

use App\Entity\Chapitre;
use App\Entity\Cours;
use App\Form\ChapitreType;
use App\Form\RechCoursType;
use App\Repository\CategorieRepository;
use App\Repository\CoursRepository;
use Doctrine\ORM\EntityManagerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class EtudiantController extends AbstractController
{
    /**
     * @Route("/etudiant", name="etudiant")
     */
    public function index(): Response
    {
        return $this->render('cours/base-front.html.twig', [
            'controller_name' => 'EtudiantController',
        ]);
    }



    /**
     * @Route("/RechercheEtudiant", name="RechercheEtudiant", methods={"GET"})
     */
    public function ClientIndex(CoursRepository $repository): Response
    {

        $cours = $repository->findAll();

        return $this->render('cours/show.html.twig', [
            'cours' => $cours,
        ]);
    }



    /**
     * @Route("/rechercher",name="rechercher")
     */
    public function testthisplz(Request $request){


        $cours =$this->getDoctrine()->getRepository(Cours::class)->listCoursByNsc();
        $form = $this->createForm(RechCoursType::class);
        $form->handleRequest($request);
        if($form->isSubmitted()){
            $nomcours=$form->getData()->getNomcours();
            $coursResult=$this->getDoctrine()->getRepository(Cours::class)->getCoursByNom($nomcours);

            return $this->render('cours/show.html.twig', [
                'courss' => $coursResult,
                'form2' => $form->createView()
            ]);
        }
        return $this->render('cours/show.html.twig', [
            'courss' => $cours,
            'form2' => $form->createView()
        ]);




    }


    ////////////////
    ///
    /**
     * @Route("/RechercheEtudiant2",name="searchc")
     */
 public function listCoursOrderBy(){
     $cours=$this->getDoctrine()->getRepository(Cours::class)->listCoursByNsc();
     return $this->render('cours/test.html.twig', [
         'courss' => $cours
     ]);
 }
///////////////////////////////////////////

    /**
     * @Route("/listecategorie", name="listecategorie", methods={"GET"})
     */
    public function ListeCategorie(CategorieRepository $repository): Response
    {

        $categorie= $repository->findAll();

        return $this->render('etudiant/listecategorie.html.twig', [
            'categ' => $categorie,
        ]);
    }

    /**
     * @Route("/cours/{id}",name="get_cours")
     */

    public function getById (CoursRepository $repository, Request $request, PaginatorInterface $paginator)
    {
        $id = $request->get('id');

        $cours = $repository->findOneBy(['id' => $id]);




        return $this->render("cours/page.html.twig",['cours' => $cours]) ;

    }

    /**
     * @Route("/categorie/{id}",name="get_categorie")
     */

    public function getCategorieById (CategorieRepository $repository, Request $request)
    {
        $id = $request->get('id');

        $categorie = $repository->findOneBy(['id' => $id]);




        return $this->render("etudiant/listecours.html.twig",['categorie' => $categorie]) ;

    }



    /**
     * @Route("/pdf2/{id}", name="pdf2" ,  methods={"GET"})
     */
    public function pdf($id,CoursRepository $repository){
$cours=$repository->find($id);

$mpdf = new \Mpdf\Mpdf();

        //$msg= $cours->getNomcours();
$mpdf->SetTitle('Cours');
$mpdf->autoScriptToLang = true;
$mpdf->autoLangToFont = true;
$ch="<img src='https://www.smallbusinessact.com/wp-content/uploads/2019/01/SBA-Types-de-contrats-de-travail.png'> <br>
  <br><br>Le cours suivant est ajouté par le formateur <strong>";
$mpdf->WriteHTML($ch);
$mpdf->Output();
    }



    /**
     * @Route("/examen", name="examen")
     */
public function examen(){
return $this->render('cours/test.html.twig', [
'aa' => 'aa',
]);
}

    /**
     * @Route("/certif", name="certif", methods={"GET","POST"})
     */
    public function pf(Request $request)
    {
        $res=$request->request->get('res');
        $this->render("cours/test.html.twig",[
            'res' => $res
        ]);
        $html = "
        <body>
            <div id='1'/>        
            <h1>
                 <center> 
                    <h2>Félicitations</h2> 
                    
     <img  width='10 %' src='https://mustaphabenhadjyahia.github.io/img/esprit.jpg',  ><br>
     


                                        Vous avez passez le test                
                     <br><br>            
                   
                     <img src='https://www.pngrepo.com/png/194568/180/qr-code.png', class='style'>
                 </center>
                     
            </h1>             
       <style>
            body {
            	background-color: #5DCAEF;
              
            }
            #1{
                 border-style : solid;
                 display : inline-block;
                 height : 0;
                 width : 0;
                 border-top : 80px solid brown;
                 border-right : 180px solid transparent;
                 border-left : 180px solid transparent;
            }
                .style{                
                max-width: 200px;
                height: 83px;
                alignment:left;  
                }
                .esprit{
                width: 10 %;
                            display : inline-block;
                                border-right : 180px solid transparent;
                 border-left : 180px solid transparent;
                 border-top : 80px solid transparent;

              

                }
            </style>
        </body>
        
        ";

        $html2 = $this->renderView('etudiant/certif.html.twig');
        $options = new Options();
        $options->setIsRemoteEnabled(true);
        $dompdf = new Dompdf($options);

        $dompdf->loadHtml($html2);
        $dompdf->setPaper('A4', 'landscape');
        $dompdf->render();
        $dompdf->stream("codexworld",array("Attachment"=>0));


    }
















}
