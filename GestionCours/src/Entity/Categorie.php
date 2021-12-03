<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity
 */
class Categorie
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
     * @Assert\NotBlank(message="Nom Categorie is required")
     * @ORM\Column(name="categorieNom", type="string", length=100, nullable=false)
     */
    private $categorienom;

    /**
     * @var string
     * @Assert\NotBlank(message="Image categorie is required")
     * @ORM\Column(name="categorieImage", type="text", length=65535, nullable=false)
     */
    private $categorieimage;

    /**
     * @ORM\OneToMany(targetEntity=Cours::class, mappedBy="Categorie",orphanRemoval=true)
     */
    private $cours;

    public function __construct()
    {
        $this->cours = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCategorienom(): ?string
    {
        return $this->categorienom;
    }

    public function setCategorienom(string $categorienom): self
    {
        $this->categorienom = $categorienom;

        return $this;
    }

    public function getCategorieimage(): ?string
    {
        return $this->categorieimage;
    }

    public function setCategorieimage(string $categorieimage): self
    {
        $this->categorieimage = $categorieimage;

        return $this;
    }

    /**
     * @return Collection|Cours[]
     */
    public function getCours(): Collection
    {
        return $this->cours;
    }

    public function addCour(Cours $cour): self
    {
        if (!$this->cours->contains($cour)) {
            $this->cours[] = $cour;
            $cour->setCategorie($this);
        }

        return $this;
    }

    public function removeCour(Cours $cour): self
    {
        if ($this->cours->removeElement($cour)) {
            // set the owning side to null (unless already changed)
            if ($cour->getCategorie() === $this) {
                $cour->setCategorie(null);
            }
        }

        return $this;
    }
    public function __toString()
    {
        return $this->getCategorienom();
    }



}
