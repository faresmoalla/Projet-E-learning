<?php

namespace App\Controller;

use App\Form\ContactType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\ORM\EntityManagerInterface;



class ContactController extends AbstractController
{
    /**
     * @Route("/contact", name="contact")
     */
    public function index(Request $request , \Swift_Mailer $mailer)
    {

        $form= $this->createForm(ContactType::class);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid()){

           $contact = $form->getData();
           // Ici pour enverron le mail
$message=(new \Swift_Message('Nouveau Contact'))
        // On attribue l'expéditeur
            ->setFrom($contact['email'])
            // on attribue le destinataire
            ->setTo('fares.moalla1996@gmail.com')
    // on crée le message avec la vue twig
            ->setBody(
                $this->renderView(
                    'email/contact.html.twig',compact('contact')
                ),
        'text/html'
    )
;
// on envoie le message
            $mailer->send($message);
            $this->addFlash('message','Le message a bien été envoye');
            return $this->redirectToRoute('contact');


        }
        return $this->render('contact/index.html.twig', [
            'contactForm' => $form->createView()
        ]);
    }


   /* public function sendmail($name, \Swift_Mailer $mailer)
    {
        $message = (new \Swift_Message('Hello Email'))
            ->setFrom('fares.moalla1996@gmail.com')
            ->setTo('9ariniphoenix@gmail.com
')
            ->setBody(
                $this->renderView(
                // templates/emails/registration.html.twig
                    'email/contact.html.twig',
                    ['name' => $name]
                ),
                'text/html'
            )

            // you can remove the following code if you don't define a text version for your emails
            ->addPart(
                $this->renderView(
                    'email/registration.txt.twig',
                    ['name' => $name]
                ),
                'text/plain'
            )
        ;

        $mailer->send($message);

       return $this->render(...);
    }*/





    public function sendEmail( $mailer, $objet, $to, $msg)
    {
        $message = (new \Swift_Message($objet))
            ->setFrom('fares.moalla1996@gmail.com')
            ->setTo($to)
            ->setBody($msg)
        ;

        $mailer->send($message);

        // ...
    }

























}
