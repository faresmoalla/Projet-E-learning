<?php

namespace App\Entity;

use App\Repository\PubliciteRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Vich\UploaderBundle\Mapping\Annotation as vich;

/**
 * @ORM\Entity(repositoryClass=PubliciteRepository::class)
 */
class Publicite
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Url(
     *    relativeProtocol = true
     * )
     */
    private $lienPublicite;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $imagePublicite;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\Url(
     *    relativeProtocol = true
     * )
     */
    private $videoPublicite;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $nbrAffichage;

    /**
     * @ORM\Column(type="integer", nullable=true)
     */
    private $nbrClick;

    /**
     * @ORM\ManyToOne(targetEntity=Cible::class, inversedBy="publicites")
     * @ORM\JoinColumn(nullable=false)({
     *   @ORM\JoinColumn(name="cible_id", referencedColumnName="id")
     */
    private $cible;


    protected $captchaCode;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getLienPublicite(): ?string
    {
        return $this->lienPublicite;
    }

    public function setLienPublicite(string $lienPublicite): self
    {
        $this->lienPublicite = $lienPublicite;

        return $this;
    }

    public function getImagePublicite(): ?string
    {
        return $this->imagePublicite;
    }

    public function setImagePublicite(string $imagePublicite): self
    {
        $this->imagePublicite = $imagePublicite;

        return $this;
    }

    public function getVideoPublicite(): ?string
    {
        return $this->videoPublicite;
    }

    public function setVideoPublicite(string $videoPublicite): self
    {
        $this->videoPublicite = $videoPublicite;

        return $this;
    }

    public function getNbrAffichage(): ?int
    {
        return $this->nbrAffichage;
    }

    public function setNbrAffichage(?int $nbrAffichage): self
    {
        $this->nbrAffichage = $nbrAffichage;

        return $this;
    }

    public function getNbrClick(): ?int
    {
        return $this->nbrClick;
    }

    public function setNbrClick(?int $nbrClick): self
    {
        $this->nbrClick = $nbrClick;

        return $this;
    }

    public function getCible(): ?cible
    {
        return $this->cible;
    }

    public function setCible(?cible $cible): self
    {
        $this->cible = $cible;

        return $this;
    }
    public function getCptchaCode()
    {
        return $this->captchacode;
    }
    public function CptchaCode($captchacode): self
    {
        $this->captchacode = $captchacode;

    }
}
