<?php

namespace App\Form;

use App\Entity\Cours;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CoursType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('utilisateurnom')
            ->add('nomcours',null,array('required'=> false))
            ->add('nbrchapitres')

            ->add('description')
            ->add('coursimg',FileType::class, array('data_class' => null))
                        ->add('Categorie')
         /*   ->add("captchaCode",CaptchaType::class,[
                'captchaConfig'=>[
                    new ValidCaptcha([
                        'message'=>'Invalid captcha, please try again'
                    ])
                ]
            ])*/

        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Cours::class,
        ]);
    }
}
