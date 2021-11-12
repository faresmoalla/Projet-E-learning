<?php

namespace App\Entity;

use App\Repository\NoteRepository;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * @ORM\Entity(repositoryClass=NoteRepository::class)
 */
class Note
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer",name="noteID")
     */
    private $id;

    /**
     * @ORM\Column(type="integer", nullable=true)
     * @Assert\Range(
     *      min =0,
     *      max = 5,
     *      notInRangeMessage = "the note must be between {{ min }} and {{ max }}",
     * )
     */
    private $notevaleur;

    /**
     * @ORM\ManyToOne(targetEntity=Utilisateur::class)
     * @ORM\JoinColumn(nullable=false,name="utilisateur_id", referencedColumnName="utilisateurID")
     *
     */
    private $utilisateur;

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNotevaleur(): ?int
    {
        return $this->notevaleur;
    }

    public function setNotevaleur(?int $notevaleur): self
    {
        $this->notevaleur = $notevaleur;

        return $this;
    }

    public function getUtilisateur(): ?Utilisateur
    {
        return $this->utilisateur;
    }

    public function setUtilisateur(?Utilisateur $utilisateur): self
    {
        $this->utilisateur = $utilisateur;

        return $this;
    }
}
