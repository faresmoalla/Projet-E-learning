<?php

namespace App\Controller;

use App\Entity\Cours;
use App\Entity\Reclamation;
use App\Form\NewReclamationType;
use App\Form\RechCoursType;
use App\Form\ReplyReclamationType;
use App\Repository\CoursRepository;
use App\Repository\ReclamationRepository;
use App\Repository\UtilisateurRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{

    /**
     * @Route("/", name="reclamation_index", methods={"GET"})
     */
    public function index(ReclamationRepository $reclamationRepo): Response
    {
        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamationRepo->findBy(["categoriereclamation" => "Paiement"]),
        ]);
    }

    /**
     * @Route("/new", name="reclamation_new", methods={"GET", "POST"})
     */
    public function new(UtilisateurRepository $utilisateurRepo, Request $request, \Swift_Mailer $mailer): Response
    {
        $connectedUser = $utilisateurRepo->findOneBy(["id" => "93"]);
        $reclamation = new Reclamation();
        $reclamation->setUtilisateurid($connectedUser);
        $reclamation->setDatereclamation(new \DateTime('now'));
        $reclamation->setEtatreclamation("En cours");
        $form = $this->createForm(NewReclamationType::class, $reclamation);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reclamation);
            $entityManager->flush();
            $message = (new \Swift_Message())
                ->setFrom('mohamedaminebenfredj1k99@gmail.com')
                ->setTo($connectedUser->getUtilisateuradresseemail())
                ->setSubject('Réclamation envoyée')
                ->setBody('<p>Votre demande sera prise en compte. Nous vous répondrons dans les meilleurs délais.</p>', 'text/html');
            $mailer->send($message);
            return $this->redirectToRoute('reclamation_index', [], Response::HTTP_SEE_OTHER);
        }
        return $this->render('reclamation/new.html.twig', ['form' => $form->createView()]);
    }

    /**
     * @Route("/{reclamationid}", name="reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response
    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @Route("/{reclamationid}/reply", name="reclamation_reply", methods={"POST"})
     */
    public function reply(Reclamation $reclamation, ReclamationRepository $reclamationRepo, Request $request): Response
    {
        $reclamation->setDatereponse(new \DateTime('now'));
        $reclamation->setEtatreclamation("Vérifiée");
        $form = $this->createForm(ReplyReclamationType::class, $reclamation);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reclamation);
            $entityManager->flush();
            return $this->redirectToRoute('reclamation_index', [], Response::HTTP_SEE_OTHER);
        }
        return $this->render('reclamation/reply.html.twig', ['form' => $form->createView()]);
    }

    /**
     * @Route("/{reclamationid}/seen", name="reclamation_seen", methods={"GET","POST"})
     */
    public function seen(
        Request $request,
        Reclamation $reclamation,
        UtilisateurRepository $utilisateurRepo,
        ReclamationRepository $reclamationRepo,
        EntityManagerInterface $entityManager): Response
    {
        $reclamation->setEtatreponse(0);
        $entityManager->persist($reclamation);
        $entityManager->flush();

        $prixTotal = 0;
        $unseen = [];
        $connectedUser = $utilisateurRepo->findOneBy(["id" => "93"]);
        $reclamations = $reclamationRepo->findBy(["utilisateurid" => $connectedUser->getId(), "etatreclamation" => "Vérifiée"]);
        foreach ($reclamations as $reclamation) {
            if ($reclamation->getEtatreponse() == 1) {
                array_push($unseen, $reclamation->getEtatreponse());
            }
        }
        $commandes = $entityManager
            ->createQueryBuilder()
            ->select('c.nbrchapitres as prixCours')
            ->from('App\Entity\Panier', 'p')
            ->innerJoin('App\Entity\Commande', 'cde', 'with', "p.panierid = cde.panierid")
            ->innerJoin('App\Entity\Cours', 'c', 'with', "cde.coursid = c.id")
            ->where('p.utilisateurid = :utilisateur')
            ->andWhere('p.etatpanier = :etatPanier')
            ->setParameter('utilisateur', $connectedUser)
            ->setParameter('etatPanier', 'En cours')
            ->getQuery()
            ->getResult();
        foreach ($commandes as $commande) {
            $prixTotal += $commande["prixCours"];
        }
        $cours = $this->getDoctrine()->getRepository(Cours::class)->listCoursByNsc();
        $form = $this->createForm(RechCoursType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $nomcours = $form->getData()->getNomcours();
            $coursResult = $this->getDoctrine()->getRepository(Cours::class)->getCoursByNom($nomcours);
            return $this->render('cours/show.html.twig', ['courss' => $coursResult, 'form2' => $form->createView()]);
        }
        return $this->render('cours/show.html.twig', [
            'courss' => $cours,
            'form2' => $form->createView(),
            'reclamations' => $reclamations,
            'unseen' => $unseen,
            'commandes' => $commandes,
            'prixTotal' => $prixTotal
        ]);
    }

    /**
     * @Route("/{reclamationid}/unseen", name="reclamation_unseen", methods={"GET","POST"})
     */
    public function unseen(
        Request $request,
        Reclamation $reclamation,
        UtilisateurRepository $utilisateurRepo,
        ReclamationRepository $reclamationRepo,
        EntityManagerInterface $entityManager): Response
    {
        $reclamation->setEtatreponse(1);
        $entityManager->persist($reclamation);
        $entityManager->flush();

        $prixTotal = 0;
        $unseen = [];
        $connectedUser = $utilisateurRepo->findOneBy(["id" => "93"]);
        $reclamations = $reclamationRepo->findBy(["utilisateurid" => $connectedUser->getId(), "etatreclamation" => "Vérifiée"]);
        foreach ($reclamations as $reclamation) {
            if ($reclamation->getEtatreponse() == 1) {
                array_push($unseen, $reclamation->getEtatreponse());
            }
        }
        $commandes = $entityManager
            ->createQueryBuilder()
            ->select('c.nbrchapitres as prixCours')
            ->from('App\Entity\Panier', 'p')
            ->innerJoin('App\Entity\Commande', 'cde', 'with', "p.panierid = cde.panierid")
            ->innerJoin('App\Entity\Cours', 'c', 'with', "cde.coursid = c.id")
            ->where('p.utilisateurid = :utilisateur')
            ->andWhere('p.etatpanier = :etatPanier')
            ->setParameter('utilisateur', $connectedUser)
            ->setParameter('etatPanier', 'En cours')
            ->getQuery()
            ->getResult();
        foreach ($commandes as $commande) {
            $prixTotal += $commande["prixCours"];
        }
        $cours = $this->getDoctrine()->getRepository(Cours::class)->listCoursByNsc();
        $form = $this->createForm(RechCoursType::class);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $nomcours = $form->getData()->getNomcours();
            $coursResult = $this->getDoctrine()->getRepository(Cours::class)->getCoursByNom($nomcours);
            return $this->render('cours/show.html.twig', ['courss' => $coursResult, 'form2' => $form->createView()]);
        }
        return $this->render('cours/show.html.twig', [
            'courss' => $cours,
            'form2' => $form->createView(),
            'reclamations' => $reclamations,
            'unseen' => $unseen,
            'commandes' => $commandes,
            'prixTotal' => $prixTotal
        ]);
    }

    /**
     * @Route("/{reclamationid}", name="reclamation_delete", methods={"POST"})
     */
    public function delete(Request $request, Reclamation $reclamation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getReclamationid(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }
        return $this->redirectToRoute('reclamation_index', [], Response::HTTP_SEE_OTHER);
    }


}
