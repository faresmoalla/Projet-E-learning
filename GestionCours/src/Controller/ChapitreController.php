<?php

namespace App\Controller;
use App\Entity\Cours;
use App\Repository\ChapitreRepository;
use App\Form\ChapitreType;
use App\Repository\CoursRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\File\Stream;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use App\Entity\Chapitre;


class ChapitreController extends AbstractController
{
    /**
     * @Route("/chapitre", name="chapitre")
     */
    public function index(): Response
    {
        return $this->render('chapitre/index.html.twig', [
            'controller_name' => 'ChapitreController',
        ]);
    }

    /**

     * @Route("/ajoutchapitre",name="ajoutchapitre")
     */
    public function add(EntityManagerInterface $em,Request $request){
        $chapitre= new Chapitre();

        $form= $this->createForm(ChapitreType::class,$chapitre);
        $form->add('Ajouter',SubmitType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){
            $imageFile = $form->get('videochapitre')->getData();
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
                $chapitre->setVideochapitre($newFilename);
            }
            $em->persist($chapitre);
            $em->flush();
            $this->addFlash(
                'info','Added successfully!'
            );
             return $this->redirectToRoute("affichechapitre");

        }
        return $this->render("chapitre/ajoutChapitre.html.twig",array("formchapitre"=>$form->createView()));
    }


    /**
     * @Route("/affichechapitre",name="affichechapitre")
     */
    public function Affiche(ChapitreRepository  $repository,Request $request, PaginatorInterface $paginator){
        $tableChapitre=$repository->findAll();

        $tableChapitre = $paginator->paginate(
            $tableChapitre,
            $request->query->getInt('page', 1),
            5
        );
        return $this->render('chapitre/affichechapitre.html.twig'
            ,['tableChapitre'=>$tableChapitre]);

    }


    /**
     * @Route("/{id}/editchapitre", name="modifierchapitre", methods={"GET","POST"})
     */
    public function edit(Request $request, Chapitre $chapitre): Response
    {
        $form = $this->createForm(ChapitreType::class, $chapitre);
        $form->add('Confirmer',SubmitType::class);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $imageFile = $form->get('videochapitre')->getData();
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
                $chapitre->setVideochapitre($newFilename);
            }
            $this->getDoctrine()->getManager()->flush();
            $this->addFlash(
                'info',
                'Vos modifications ont été enregistrées!'
            );

            return $this->redirectToRoute('affichechapitre');
        }

        return $this->render('chapitre/Modifierchapitre.html.twig', [
            'chapitremodif' => $chapitre,
            'form' => $form->createView(),
        ]);
    }






    /**
     * @Route("/supprimerchapitre/{id}",name="supprimerchapitre")
     */
    public function deleteChapitre($id,EntityManagerInterface $em ,ChapitreRepository $repository){
        $chapitre=$repository->find($id);
        $em->remove($chapitre);
        $em->flush();
        $this->addFlash(
            'info','Supprimé avec succées '
        );
        // $flashy->success('Event created!', 'http://your-awesome-link.com');
        return $this->redirectToRoute('affichechapitre');
    }




    /**
     * @Route("/showPDF/{id}", name="showPDF")
     */
    public function showAction($id)
    {
      /*  $em = $this->getDoctrine()->getManager();
        $item = $em->getRepository(Chapitre::class)->find($id);

        if (!$item) {
            throw $this->createNotFoundException("File with ID $id does not exist!");
        }
        $pdfFile = stream_get_contents($item->getR0essource());
        //$pdfFile = $item->getRessource(); //returns pdf file stored as mysql blob
        $stream = new Stream("front\\web\\images".$pdfFile);

        //$response = new Response( readfile($pdfFile), 200, array('Content-Type' => 'application/pdf'));

        return new BinaryFileResponse($stream);
*/
    }


    /**
     * @Route("/show/chapitre/{coursid}",name="showchapitre")
     */

    public function getChapitre (EntityManagerInterface $em , Request $request , CoursRepository  $repository, PaginatorInterface $paginator)
    {

        $chapitre = new Chapitre();
        $cours = $repository->findOneBy(['id' => $request->get('cours_id')]);

        $chapitre->setCours($cours) ;
        $chapitre->setChapitrenom($request->get('chapitrenom'));
        $chapitre->setVideochapitre($request->get('videochapitre'));
        $chapitre->setImage($request->get('image'));

        $em->persist($chapitre);
        $em->flush();

        return $this->redirectToRoute('get_cours', ['id' =>$request->get('cours_id')]);

    }




}
