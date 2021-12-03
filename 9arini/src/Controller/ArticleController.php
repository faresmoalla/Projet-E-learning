<?php

namespace App\Controller;

use App\Entity\Article;
use App\Entity\PostDislike;
use App\Entity\PostLike;
use App\Form\ArticleType;
use App\Repository\ArticleRepository;
use App\Repository\CommentsRepository;
use App\Repository\PostDislikeRepository;
use App\Repository\PostLikeRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\EntityManager;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\Validator\Constraints as Assert;

class ArticleController extends AbstractController
{
    /**
     * @Route("/article", name="article")
     */
    public function index(): Response
    {
        return $this->render('article/index.html.twig');
    }

    /**
     * @Route("/affichearticle",name="affichearticle")
     */


    public function AfficheArticle(ArticleRepository $repository){
        $tablearticle=$repository->findAll();
        return $this->render('article/afficheArticle.html.twig'
            ,['tableArticle'=>$tablearticle]);

    }


    /**
     * @Route("/ajoutarticle",name="ajoutarticle")
     */

    public function addArticle (EntityManagerInterface $em , Request $request,\Swift_Mailer $mailer)
    {

        $article = new Article();

        $form = $this->createForm(ArticleType::class, $article);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
           $new=$form->getData();
            $em->persist($article);
            $em->flush();
            $message = (new \Swift_Message('Vous avez ajouté un nouveau Article'))
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
            return $this->redirectToRoute("affichearticle");
        }
        return $this->render("article/addarticle.html.twig", array("form" => $form->createView()));

    }

    /**
     * @Route("/article/{id}",name="get_article")
     */

    public function getById (ArticleRepository $repository, Request $request , CommentsRepository $commentsrepository)
    {

        $id = $request->get('id');
        $article = $repository->findOneBy(['id' => $id]);
        $comments = $commentsrepository->findBy(['article' =>$article]);
        $article->setComments($comments) ;
        return $this->render("article/page.html.twig",['article' => $article]) ;

    }


    /**
     * @Route("/add/like/{articleId}",name="add_like")
     */

    public function addLike (ArticleRepository $repository, Request $request, EntityManagerInterface $em) {
        $like = new PostLike() ;
        $article = $repository->findOneBy(['id' => $request->get('articleId')]);
        $like->setArticle($article);
        $like->setUtilisateur($request->get('utilisateur_id'));

        $em->persist($like);
        $em->flush();

        return $this->redirectToRoute('affichearticle', ['id' =>$request->get('articleId')]);

    }


    /**
     * @Route("/add/dislike/{articleId}",name="add_dislike")
     */

    public function addDisLike (ArticleRepository $repository, Request $request, EntityManagerInterface $em) {
        $dislike = new PostDislike() ;
        $article = $repository->findOneBy(['id' => $request->get('articleId')]);
        $dislike->setArticle($article);
        $dislike->setUtilisateur($request->get('utilisateur_id'));

        $em->persist($dislike);
        $em->flush();

        return $this->redirectToRoute('affichearticle', ['id' =>$request->get('articleId')]);

    }



    /**
     * @Route("/get/dislike/{articleId}",name="get_dislike")
     */


    public function getDisLike (Article $article , ArticleRepository $repository, Request $request, EntityManagerInterface $em, PostDislikeRepository $dislikerepository) {
        $nbrdis = $dislikerepository->findBy($article->getId()) ;
        return $this->render("article/afficheArticle.html.twig",compact('nbrdis')) ;
    }





}
