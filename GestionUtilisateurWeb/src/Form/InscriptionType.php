<?php

namespace App\Form;

use App\Entity\Utilisateur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\RepeatedType;

class InscriptionType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder

            ->add('utilisateurprenom')
            ->add('utilisateurnom')
            ->add('utilisateurgenre', ChoiceType::class,[
                'choices' => [
                    'homme' => 'homme',
                    'femme' => 'femme',

                ],
                'expanded' => true
            ])
            ->add('utilisateurddn')
            ->add('utilisateuradresseemail')
            ->add('utilisateurmdp', RepeatedType::class, [
                'first_options'  => ['label' => 'mot de passe '],
                'second_options' => ['label' => 'confirmer mot de passe'],
            ])
            ->add('utilisateurrole', ChoiceType::class,[
                'choices' => [
                    'Membre' => 'Membre',
                    'Formateur' => 'Formateur',
                    'Entrepreneur' => 'Entrepreneur',
                ],
                'expanded' => true
            ])
            ->add("submit",SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Utilisateur::class,
        ]);
    }
}
