<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Cours
 *
 * @ORM\Table(name="cours", indexes={@ORM\Index(name="FK_UserNom", columns={"utilisateurNom"})})
 * @ORM\Entity(repositoryClass="App\Repository\CoursRepository")
 */
class Cours
{
    /**
     * @var int
     *
     * @ORM\Column(name="coursID", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $coursid;

    /**
     * @var string
     *
     * @ORM\Column(name="utilisateurNom", type="string", length=100, nullable=false)
     */
    private $utilisateurnom;

    /**
     * @var string
     *
     * @ORM\Column(name="nomCours", type="string", length=50, nullable=false)
     */
    private $nomcours;

    /**
     * @var int
     *
     * @ORM\Column(name="nbrChapitres", type="integer", nullable=false)
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
     * @var string
     *
     * @ORM\Column(name="categorieNom", type="string", length=100, nullable=false)
     */
    private $categorienom;

    public function getCoursid(): ?int
    {
        return $this->coursid;
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














}