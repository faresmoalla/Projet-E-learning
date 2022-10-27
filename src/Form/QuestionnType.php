<?php

namespace App\Form;

use App\Entity\Questionn;
use App\Entity\Quizz;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class QuestionnType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('idquiz',EntityType::class,[
                'class'=>Quizz::class,
                'choice_label'=>'nom'
            ])
            ->add('question')
            ->add('option1')
            ->add('option2')
            ->add('option3')
            ->add('option4')
            ->add('reponse')


        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Questionn::class,
        ]);
    }
}
