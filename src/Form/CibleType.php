<?php

namespace App\Form;

use App\Entity\Cible;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CibleType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('genre',
                ChoiceType::class,
                [
                    'choices' => [""=>"",
                        'Tous' => "Tous",
                        'Homme' => "Homme",
                        'Famme' => "Famme",
                    ],
                ])
            ->add('ageMin',ChoiceType::class,
                [
                    'choices' => [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,45,46,47,48,49,50]
                ]
            )
            ->add('ageMax',ChoiceType::class,
                [
                    'choices' => [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,45,46,47,48,49,50]
                ]
            )

            ->add('dateDebut', DateType::class, [
                'widget' => 'choice',

            ])
            ->add('dateFin', DateType::class, [
                'widget' => 'choice',

            ])

            ->add('prix')
            ->add('utilisateur')
            ->add('valider',SubmitType::class)


        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cible::class,
        ]);
    }
}
