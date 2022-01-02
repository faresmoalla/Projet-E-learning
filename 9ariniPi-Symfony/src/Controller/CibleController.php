<?php

namespace App\Controller;

use App\Entity\Cible;
use App\Form\CibleType;
use App\Repository\CibleRepository;
use App\Repository\PubliciteRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CibleController extends AbstractController
{
    /**
     * @Route("/cible", name="cible")
     */
    public function index(): Response
    {
        return $this->render('cible/show.html.twig', [
            'controller_name' => 'CibleController',
        ]);
    }
    /**
     * @Route("/addCible", name="cibleadd")
     */

    public function add(EntityManagerInterface $em,Request $request, \Swift_Mailer $mailer,FlashyNotifier $flashy){

        $cible = new cible();


        $form= $this->createForm(CibleType::class,$cible);

        $form->handleRequest($request);


        $msg="";

        if($form->isSubmitted() && $form->isValid()){
            $a=$cible->getDateDebut()->format('y-m-d');
            $b=$cible->getDateFin()->format('y-m-d');

            if ($b>$a)
            {
                $em->persist($cible);
                $em->flush();
                $message = (new \Swift_Message())
                    ->setFrom('fares.moalla1996@gmail.com')
                    ->setTo('kacemradhwen@gmail.com')
                    ->setSubject('Cible ajouteé')
                    ->setBody('<p>votre création de cible à été ajouté. Nous vous répondrons dans les meilleurs délais.</p>', 'text/html');
                $mailer->send($message);
                return $this->redirectToRoute('liscible', [], Response::HTTP_SEE_OTHER);
                $flashy->success('Ajouté avec succées !', 'http://your-awesome-link.com');
                $this->addFlash(
                    'info','Ajouté avec succées '
                );

            } else {
                $msg=". Ajouter la date de fin supérieure à la date de début";
                return $this->render("cible/ajoutcible.html.twig",array("form"=>$form->createView(),"msg"=>$msg));
            }


            return $this->redirectToRoute("liscible");
        }
        return $this->render("cible/ajoutcible.html.twig",array("form"=>$form->createView(),"msg"=>$msg));
    }


    /**
     * @Route("/listcible",name="liscible")
     */

    public function listCible(Request $request, PaginatorInterface $paginator){
        $cibles=$this->getDoctrine()->getRepository(Cible::class)->findAll();
        $list = $paginator->paginate(
            $cibles, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            6 // Nombre de résultats par page
        );

        return $this->render("cible/list.html.twig", [
            'cible' => $list,
        ]);
    }


    /**
     * @Route("/deleteCible/{id}",name="deleteCinbe")
     */
    public function remove($id,EntityManagerInterface $em,FlashyNotifier $flashy)
    {
        $cible= $this->getDoctrine()->getRepository(Cible::class)->find($id);
        $em->remove($cible);
        $em->flush();
        $flashy->muted('Supprimé avec succées ', 'http://your-awesome-link.com');
        $this->addFlash(
            'info','Supprimé avec succées '
        );
        return $this->redirectToRoute("liscible");
    }
    /**
     * @Route("/updateCible/{id}",name="updateCible")
     */
    public function update(Request $request,$id,FlashyNotifier $flashy)
    {
        $cible=$this->getDoctrine()->getRepository(Cible::class)->find($id);
        $form= $this->createForm(CibleType::class,$cible);
        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            $flashy->primary('Modifié avec succées !', 'http://your-awesome-link.com');
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );
            return $this->redirectToRoute("liscible");
        }
        return $this->render("cible/updatecible.html.twig",array("formulaire"=>$form->createView()));
    }


    /**
     * @Route("/listadmincible",name="liscibleadmin")
     */

    public function listCibleAdmin(Request $request, PaginatorInterface $paginator){
        $cibles=$this->getDoctrine()->getRepository(Cible::class)->findAll();
        $list = $paginator->paginate(
            $cibles,
            $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            6 // Nombre de résultats par page
        );

        return $this->render("admin/listcible.html.twig",array("cible"=>$list));
    }




    /**
     * @Route("/deleteCibleAdmin/{id}",name="deleteCinbeAdmin")
     */
    public function removeAdmin($id,EntityManagerInterface $em,FlashyNotifier $flashy)
    {
        $cible= $this->getDoctrine()->getRepository(Cible::class)->find($id);
        $em->remove($cible);
        $em->flush();
        $flashy->muted('Supprimé avec succées ', 'http://your-awesome-link.com');
        $this->addFlash(
            'info','Supprimé avec succées '
        );
        return $this->redirectToRoute("liscibleadmin");
    }


}
