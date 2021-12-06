<?php

namespace App\Controller;

use App\Entity\Article;
use App\Entity\Comments;
use App\Form\ArticleType;
use App\Form\CommentsType;
use App\Repository\ArticleRepository;
use App\Repository\CommentsRepository ;
use Doctrine\ORM\EntityManagerInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;


class CommentsController extends AbstractController
{


    /**
     * @Route("/add/comment/{articleId}",name="add_comment")
     */


    public function addComment (FlashyNotifier $flashy,EntityManagerInterface $em , Request $request , ArticleRepository $repository)
    {
        $comment = new Comments();
        $article = $repository->findOneBy(['id' => $request->get('articleId')]);
        $comment->setArticle($article) ;
        $comment->setContent($request->get('content'));
        $comment->setCreatedAt(new \DateTimeImmutable());
        $comment->setUtilisateur($request->get('utilisateur_id'));

        $em->persist($comment);
        $em->flush();
        $flashy->success(' Commentaire ajouté avec succés ');

        $currentUser = 30;


        return $this->redirectToRoute('get_article', ['id' =>$request->get('articleId')]);

    }



    /**
     * @Route("/delete/{id}", name="comment_delete")
     */
    /*
    public function delete($id)
    {

        $em = $this->getDoctrine()->getManager();
        $comment = $em->getRepository(Comments::class)->find($id);
        $em->remove($comment);
        $em->flush();
        return $this->redirectToRoute('add_comment');
    }

    /**
     * @Route("comment/edit/{id}", name="comment_edit")
     */
    /*
    public function edit(Request $request, Comments $comment): Response
    {
        $form = $this->createForm(CommentsRepository::class, $comment);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('add_comment');
        }

        return $this->render('article/edit.html.twig', [
            'comment' => $comment,
            'form' => $form->createView(),
        ]);
    }



*/

}
