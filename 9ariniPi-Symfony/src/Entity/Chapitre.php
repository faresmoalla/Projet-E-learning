<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Vich\UploaderBundle\Mapping\Annotation as Vich;

/**
 * Chapitre
 *
 * @ORM\Entity(repositoryClass="App\Repository\ChapitreRepository")
 * @Vich\Uploadable
 */
class Chapitre
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;


    /**
     * @var string
     * @Assert\NotBlank(message="Nom Chapitre is required")
     * @ORM\Column(name="chapitreNom", type="string", length=100, nullable=false)
     */
    private $chapitrenom;

    /**
     * @var string
     * @Assert\NotBlank(message="Video chapitre is required")
     * @ORM\Column(name="videoChapitre", type="text", length=65535, nullable=false)
     */
    private $videochapitre;




    /**
     * @var \Chapitre
     * @ORM\ManyToOne(targetEntity=Cours::class, inversedBy="Chapitre")
     * @ORM\JoinColumn(nullable=true)({
     *  @ORM\JoinColumn(name="cours_id", referencedColumnName="id")
     * })
     */
    private $cours;





    /**
     * @ORM\Column(type="string", length=255)
     * @var string
     */
    private $image;

    /**
     * @Vich\UploadableField(mapping="product_images", fileNameProperty="image")
     * @var File
     */
    private $imageFile;

    public function getId(): ?int
    {
        return $this->id;
    }
    public function setImageFile( $image = null)
    {
        $this->imageFile = $image;

        // VERY IMPORTANT:
        // It is required that at least one field changes if you are using Doctrine,
        // otherwise the event listeners won't be called and the file is lost
        if ($image) {
            // if 'updatedAt' is not defined in your entity, use another property
            $this->updatedAt = new \DateTime('now');
        }
    }

    public function getImageFile()
    {
        return $this->imageFile;
    }

    public function setImage($image)
    {
        $this->image = $image;
    }

    public function getImage()
    {
        return $this->image;
    }


    public function getChapitrenom(): ?string
    {
        return $this->chapitrenom;
    }

    public function setChapitrenom(string $chapitrenom): self
    {
        $this->chapitrenom = $chapitrenom;

        return $this;
    }

    public function getVideochapitre(): ?string
    {
        return $this->videochapitre;
    }

    public function setVideochapitre(string $videochapitre): self
    {
        $this->videochapitre = $videochapitre;

        return $this;
    }

    public function getCours(): ?Cours
    {
        return $this->cours;
    }

    public function setCours(?Cours $cours): self
    {
        $this->cours = $cours;

        return $this;
    }
   /* public function getRessource()
    {
        return $this->ressource;
    }

    public function setRessource($ressource): self
    {
        $this->ressource = $ressource;

        return $this;
    }*/





}
