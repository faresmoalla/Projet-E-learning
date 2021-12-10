<?php

namespace App\Controller;


use App\Entity\Publicite;
use App\Form\PubliciteType;
use App\Repository\CibleRepository;
use App\Repository\PubliciteRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\Routing\Annotation\Route;




class PubliciteController extends AbstractController
{
    /**
     * @Route("/publicite", name="publicite")
     */
    public function index(): Response
    {
        return $this->render('publicite/index.html.twig', [
            'controller_name' => 'PubliciteController',
        ]);
    }



    /**
     * @Route("/listpub", name="listpub")
     */
    public function listPubliciite(Request $request, PaginatorInterface $paginator){
        $Publicites=$this->getDoctrine()->getRepository(Publicite::class)->findAll();
        $list = $paginator->paginate(
            $Publicites, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            6 // Nombre de résultats par page
        );
        return $this->render("publicite/listPublicite.html.twig",array("tabpub"=>$list));
    }


    /**
     * @Route("/addpub", name="addpub")
     */

    public function addpub(EntityManagerInterface $em,Request $request, FlashyNotifier $flashy) :Response
    {
        $Publicite = new Publicite();
        $form= $this->createForm(PubliciteType::class,$Publicite);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid()){
            $imageFile = $form->get('imagePublicite')->getData();
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
                $Publicite->setImagePublicite($newFilename);
            }

            $em->persist($Publicite);
            $em->flush();
            $flashy->success('Ajouté avec succées !', 'http://your-awesome-link.com');

            $this->addFlash(
                'info','Ajouté avec succées '
            );


            return $this->redirectToRoute('listpub', [], Response::HTTP_SEE_OTHER);


        }

        return $this->render("publicite/addpub.html.twig",array("formulaire"=>$form->createView()));
    }


    /**
     * @Route("/deletepublicite/{id}",name="deletPublicite")
     */
    public function remove($id,EntityManagerInterface $em,FlashyNotifier $flashy)
    {
        $publicite= $this->getDoctrine()->getRepository(Publicite::class)->find($id);
        $em->remove($publicite);
        $em->flush();
        $flashy->muted('Supprimé avec succées ', 'http://your-awesome-link.com');
        $this->addFlash(
            'info','Supprimé avec succées '
        );

        return $this->redirectToRoute("listpub");
    }


    /**
     * @Route("/updatePublicite/{id}",name="updatePub")
     */
    public function update(Request $request,$id,FlashyNotifier $flashy)
    {
        $publicite=$this->getDoctrine()->getRepository(Publicite::class)->find($id);
        $form= $this->createForm(PubliciteType::class,$publicite);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            $flashy->primary('Modifié avec succées !', 'http://your-awesome-link.com');

            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );
            return $this->redirectToRoute("listpub");
        }
        return $this->render("publicite/updatepub.html.twig",array("formulaire"=>$form->createView()));
    }


    /**
     * @Route("/listadminpub", name="listpubadmin")
     */
    public function listPubliciteAdmin(Request $request, PaginatorInterface $paginator){
        $Publicites=$this->getDoctrine()->getRepository(Publicite::class)->findAll();
        $list = $paginator->paginate(
            $Publicites, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            6 // Nombre de résultats par page
        );

        return $this->render("admin/listPublicite.html.twig",array("tabpub"=> $list));
    }


    /**
     * @Route("/deletepubliciteAdmin/{id}",name="deletPubliciteAdmin")
     */
    public function removeAdmin($id,EntityManagerInterface $em,FlashyNotifier $flashy)
    {
        $publicite= $this->getDoctrine()->getRepository(Publicite::class)->find($id);
        $em->remove($publicite);
        $em->flush();
        $flashy->muted('Supprimé avec succées ', 'http://your-awesome-link.com');
        $this->addFlash(
            'info','Supprimé avec succées '
        );

        return $this->redirectToRoute("listpubadmin");
    }




    /**
     * @Route("/testimage", name="testimage")
     */
    public function test(): Response
    {
        return $this->render('cours.html.twig', [
            'controller_name' => 'PubliciteController',
        ]);
    }

    /**
     * @Route("/afficheimagePub", name="afficheimagePub")
     */
    public function afficheimagePub(PubliciteRepository $repository)
    {
        $pubA =$repository->AffichePub();
        $pubB = $repository->incrimenteNbrAffiche();
        return $this->render('cours.html.twig', [
            'pub' => $pubA,$pubB]);

    }
    /**
     * @Route("/NbrClick/{id}", name="NbrClick")
     */
    public function inc(PubliciteRepository $repository,$id)
    {
        $publicite=$this->getDoctrine()->getRepository(Publicite::class)->find($id);

        $em= $this->getDoctrine()->getManager();
        $publicite->setNbrClick($publicite->getNbrClick()+1);
        $em->flush();
        $this->addFlash(
            'info',
            'Vos modifications ont été enregistrées!'
        );
        return new RedirectResponse($publicite->getlienPublicite());
    }







}
