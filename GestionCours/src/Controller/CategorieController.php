<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Form\CategorieType;
use App\Repository\CategorieRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CategorieController extends AbstractController
{
    /**
     * @Route("/categorie", name="categorie")
     */
    public function index(): Response
    {
        return $this->render('categorie/index.html.twig', [
            'controller_name' => 'CategorieController',
        ]);
    }

    /**
     * @Route("/afficheCategorie",name="affichecategorie")
     */
    public function AfficheCategorie(CategorieRepository $repository){
        $tablecategorie=$repository->findAll();
        return $this->render('categorie/afficheCategorie.html.twig'
            ,['tableCategorie'=>$tablecategorie]);

    }







    /**
     * @Route("/ajoutcategorie",name="ajoutcategorie")
     */
    public function addCategorie(EntityManagerInterface $em,Request $request){
        $categorie= new Categorie();

        $form= $this->createForm(CategorieType::class,$categorie);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $imageFile = $form->get('categorieimage')->getData();
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
                $categorie->setCategorieImage($newFilename);
            }
            $em->persist($categorie);
            $em->flush();
            $this->addFlash(
                'info','Added successfully!'
            );
            return $this->redirectToRoute("affichecategorie");

        }
        return $this->render("categorie/ajoutcategorie.html.twig",array("formulaire"=>$form->createView()));
    }

    /**
     * @Route("/supprimercategorie/{categorieID}",name="supprimercategorie")
     */
    public function deleteCategorie($categorieID,EntityManagerInterface $em ,CategorieRepository $repository){
        $categorie=$repository->find($categorieID);
        $em->remove($categorie);
        $em->flush();
        return $this->redirectToRoute('affichecategorie');
    }


    /**
     * @Route("/{categorieid}/modifiercategorie", name="modifiercategorie", methods={"GET","POST"})
     */
    public function edit(Request $request, Categorie $categorie): Response
    {
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->add('Confirmer',SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('categorieimage')->getData();
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
                $categorie->setCategorieimage($newFilename);
            }
            $this->getDoctrine()->getManager()->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );

            return $this->redirectToRoute('affichecategorie');
        }

        return $this->render('categorie/Modifiercategorie.html.twig', [
            'categoriemodif' => $categorie,
            'form' => $form->createView(),
        ]);
    }


}
