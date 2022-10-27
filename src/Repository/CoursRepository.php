<?php

namespace App\Repository;
use App\Entity\Publicite;
use App\Entity\Cours;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Cours|null find($id, $lockMode = null, $lockVersion = null)
 * @method Cours|null findOneBy(array $criteria, array $orderBy = null)
 * @method Cours[]    findAll()
 * @method Cours[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CoursRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Cours::class);
    }


    public function findCoursbyNom(){
        $entityManager = $this->getEntityManager();
        $query = $entityManager
            ->createQuery('SELECT c FROM App\Entity\Cours c Where c.id LIKE :id');
        return $query->getResult();
    }

    /**
     *
     * Requête QueryBuilder
     */
    public function getCoursByNom($nomcours){
        return $this->createQueryBuilder('c')
            ->where('c.nomcours LIKE :nomcours ')
            ->setParameter('nomcours' ,  '%' .$nomcours. '%')

            // ->setParameter('nomcours' ,  '%  $nomcours  %')
            ->getQuery()
            ->execute();
    }


    /**
     *
     * Requête QueryBuilder
     */
    public function getCoursByAnythink($nomcours){
        return $this->createQueryBuilder('c')
            ->where('c.nomcours LIKE :nomcours AND c.nomcours LIKE :utilisateurnom ')
            ->setParameter('nomcours' ,  '%' .$nomcours. '%')

            // ->setParameter('nomcours' ,  '%  $nomcours  %')
            ->getQuery()
            ->execute();
    }












    /**
     *
     * Requête QueryBuilder
     */
    public function listCoursByNsc(){
        return $this->createQueryBuilder('c')
            ->orderBy('c.nbrchapitres','DESC')
            ->getQuery()
            ->getResult()

            ;

    }






    public function searchcccc($criteria){

        return $this->createQueryBuilder('c')
            ->where('c.nomcours LIKE :nomcours')
            ->setParameter("nomcours", $criteria->getNomcours())
            ->getQuery()
            ->getResult()
            ;
    }



    // /**
    //  * @return Cours[] Returns an array of Cours objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('c.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }*/


    /*
    public function findOneBySomeField($value): ?Cours
    {
        return $this->createQueryBuilder('c')
            ->andWhere('c.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
