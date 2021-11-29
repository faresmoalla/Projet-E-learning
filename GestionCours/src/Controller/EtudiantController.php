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













}
