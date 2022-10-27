<?php

namespace App\Form;

use App\Entity\Tarif;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TarifType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('prixGenreTous',TextType::class,[])
            ->add('prixGenreHomme',TextType::class,[])
            ->add('prixGenreFamme',TextType::class,[])
            ->add('prixAges',TextType::class,[])
            ->add('prixDuree',TextType::class,[])
            ->add('nbPubParCible',TextType::class,[])
            ->add('valider',SubmitType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Tarif::class,
        ]);
    }
}
