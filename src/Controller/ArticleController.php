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
use App\Repository\UtilisateurRepository;
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
        return $this->render('article/show.html.twig');
    }



    /**
     * @Route("/affichearticle",name="affichearticle")
     */


    public function AfficheArticle(ArticleRepository $repository, PostLikeRepository  $likesRepo, PostDislikeRepository $dislikesRepo, Request $request){
        $tablearticle=$repository->findAll();
        for ($i = 0; $i < sizeof($tablearticle); $i++) {
            $likes = $likesRepo->findBy(['article' =>$tablearticle[$i]]);
            $dislikes = $dislikesRepo->findBy(['article' =>$tablearticle[$i]]);
            $tablearticle[$i]->setNbLikes(sizeof($likes));
            $tablearticle[$i]->setNbDislike(sizeof($dislikes));

        }

        $session = $request->getSession();
        $session->start();
        $session->save();
        $Utilisateur=$this->getUser();
        $session->set('user', $Utilisateur);

        $currentUser=$this->getUser()->getId();


        return $this->render('article/afficheArticle.html.twig'
            ,['tableArticle'=>$tablearticle,
                'currentUser'=>$currentUser]);

    }

    /**
     * @Route("/backArticle",name="backArticle")
     */


    public function backArticle(ArticleRepository $repository, PostLikeRepository  $likesRepo, PostDislikeRepository $dislikesRepo){
        $tablearticle=$repository->findAll();


        return $this->render('article/backarticle.html.twig'
            ,['tableArticle'=>$tablearticle
            ]);

    }

    /**
     * @Route("/ajoutarticle/{user}",name="ajoutarticle")
     */

    public function addArticle (EntityManagerInterface $em , UtilisateurRepository  $repositoryUti, Request $request,\Swift_Mailer $mailer)
    {

        $article = new Article();
        $currentUser = $repositoryUti->findOneBy(['id' =>$request->get('user')]);
        $form = $this->createForm(ArticleType::class, $article);
        $form->add('Ajouter', SubmitType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $new=$form->getData();
            $article->setUtilisateur($currentUser);
            $em->persist($article);
            $em->flush();
            $message = (new \Swift_Message('Vous avez ajouté un nouveau Article'))
                ///expediteur
                ->setFrom('fares.moalla1996@gmail.com')
                ///destinataire
                ->setTo('mohamedyahia.maammar@esprit.tn')
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
     * @Route("article/edit/{id}", name="article_edit")
     */
    public function edit(Request $request, Article $article): Response
    {
        $form = $this->createForm(ArticleType::class, $article);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('affichearticle');
        }

        return $this->render('article/edit.html.twig', [
            'article' => $article,
            'form' => $form->createView(),
        ]);
    }







    /**
     * @Route("/article/{id}/{user}",name="get_article")
     */

    public function getById (ArticleRepository $repository, Request $request , CommentsRepository $commentsrepository)
    {

        $id = $request->get('id');
        $article = $repository->findOneBy(['id' => $id]);
        $comments = $commentsrepository->findBy(['article' =>$article]);
        $article->setComments($comments) ;
        //dump($article);die();
        return $this->render("article/page.html.twig",['article' => $article,'currentUser'=>$request->get('user')]) ;

    }


    /**
     * @Route("/add/like/{articleId}/{user}",name="add_like")
     */

    public function addLike (ArticleRepository $repository, UtilisateurRepository  $repositoryUti,PostLikeRepository  $repositoryLike,Request $request, EntityManagerInterface $em) {
        $like = new PostLike() ;
        $article = $repository->findOneBy(['id' => $request->get('articleId')]);
        $currentUser = $repositoryUti->findOneBy(['id' =>$request->get('user')]);
        $liked = $repositoryLike->findOneBy(['article' => $article,'utilisateur'=>$currentUser]);
        //dump($liked);die();
        $like->setArticle($article);
        $like->setUtilisateur($currentUser);
        if($liked == null){
            $em->persist($like);
        }else{
            $em->remove($liked);
        }
        $em->flush();
        return $this->redirectToRoute('affichearticle', ['id' =>$request->get('articleId')]);

    }


    /**
     * @Route("/add/dislike/{articleId}/{user}",name="add_dislike")
     */

    public function addDisLike (ArticleRepository $repository, UtilisateurRepository  $repositoryUti,PostDislikeRepository $repositorydisLike,Request $request, EntityManagerInterface $em) {
        $dislike = new PostDislike() ;
        $article = $repository->findOneBy(['id' => $request->get('articleId')]);
        $currentUser = $repositoryUti->findOneBy(['id' =>$request->get('user')]);
        $disliked = $repositorydisLike->findOneBy(['article' => $article,'utilisateur'=>$currentUser]);



        $dislike->setArticle($article);
        $dislike->setUtilisateur($currentUser);

        if($disliked == null){
            $em->persist($dislike);
        }else{
            $em->remove($disliked);
        }
        $em->flush();

        return $this->redirectToRoute('affichearticle', ['id' =>$request->get('articleId')]);

    }

    /**
     * @Route("/delete/{id}", name="article_delete")
     */
    public function delete($id)
    {

        $em = $this->getDoctrine()->getManager();
        $article = $em->getRepository(Article::class)->find($id);
        $em->remove($article);
        $em->flush();
        return $this->redirectToRoute('affichearticle');
    }

    /**
     * @Route("/deletee/{id}", name="back_delete")
     */
    public function delete_back($id)
    {

        $em = $this->getDoctrine()->getManager();
        $article = $em->getRepository(Article::class)->find($id);
        $em->remove($article);
        $em->flush();
        return $this->redirectToRoute('backArticle');

    }



}
