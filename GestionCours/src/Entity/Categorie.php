<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

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
     * @ORM\Column(name="categorieID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $categorieid;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieNom", type="string", length=100, nullable=false)
     */
    private $categorienom;

    /**
     * @var string
     *
     * @ORM\Column(name="categorieImage", type="text", length=65535, nullable=false)
     */
    private $categorieimage;

    public function getCategorieid(): ?int
    {
        return $this->categorieid;
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


}
