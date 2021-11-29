<?php

namespace App\Entity;

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Cours
 *
 * @ORM\Table(name="cours", indexes={@ORM\Index(name="FK_UserNom", columns={"utilisateurNom"})})
 * @ORM\Entity(repositoryClass="App\Repository\CoursRepository")
 *
 */
class Cours
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
     * @ORM\Column(name="utilisateurNom", type="string", length=100, nullable=false)
     */
    private $utilisateurnom;

    /**
     * @var string
     * @ORM\Column(name="nomCours", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="Nom cours is required")

     */
    private $nomcours;

    /**
     * @var int
     * @Assert\NotBlank(message="Nom Cours is required")
     * @ORM\Column(name="nbrChapitres", type="integer", nullable=false)
     *
     */
    private $nbrchapitres;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="text", length=65535, nullable=false)
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="coursImg", type="string", length=5000, nullable=false)
     */
    private $coursimg;


    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity=Categorie::class, inversedBy="cours")
     * @ORM\JoinColumn(nullable=false)({
     *   @ORM\JoinColumn(name="categorie_id", referencedColumnName="id")
     * })
     */
    private $Categorie;

    /**
     * @ORM\OneToMany(targetEntity=Chapitre::class, mappedBy="cours", orphanRemoval=true)
     */
    private $Chapitre;

    public function __construct()
    {
        $this->Chapitre = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }

    public function getUtilisateurnom(): ?string
    {
        return $this->utilisateurnom;
    }

    public function setUtilisateurnom(string $utilisateurnom): self
    {
        $this->utilisateurnom = $utilisateurnom;

        return $this;
    }

    public function getNomcours(): ?string
    {
        return $this->nomcours;
    }

    public function setNomcours(string $nomcours): self
    {
        $this->nomcours = $nomcours;

        return $this;
    }

    public function getNbrchapitres(): ?int
    {
        return $this->nbrchapitres;
    }

    public function setNbrchapitres(int $nbrchapitres): self
    {
        $this->nbrchapitres = $nbrchapitres;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getCoursimg(): ?string
    {
        return $this->coursimg;
    }

    public function setCoursimg(string $coursimg): self
    {
        $this->coursimg = $coursimg;

        return $this;
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



    //////////////////////////
    public function getWebpath(){
        return null === $this->coursimg ? null : $this->getUploadDir.'/'.$this->coursimg;
    }
    protected  function  getUploadRootDir(){

        return __DIR__.'/../../../front/web/images'.$this->getUploadDir();
    }
    protected function getUploadDir(){

        return'';
    }

    public function getCategorie(): ?Categorie
    {
        return $this->Categorie;
    }

    public function setCategorie(?Categorie $Categorie): self
    {
        $this->Categorie = $Categorie;

        return $this;
    }


    public function __toString()
    {
        return $this->getNomcours();
    }

    /**
     * @return Collection|Chapitre[]
     */
    public function getChapitre(): Collection
    {
        return $this->Chapitre;
    }

    public function addChapitre(Chapitre $chapitre): self
    {
        if (!$this->Chapitre->contains($chapitre)) {
            $this->Chapitre[] = $chapitre;
            $chapitre->setCours($this);
        }

        return $this;
    }

    public function removeChapitre(Chapitre $chapitre): self
    {
        if ($this->Chapitre->removeElement($chapitre)) {
            // set the owning side to null (unless already changed)
            if ($chapitre->getCours() === $this) {
                $chapitre->setCours(null);
            }
        }

        return $this;
    }













}