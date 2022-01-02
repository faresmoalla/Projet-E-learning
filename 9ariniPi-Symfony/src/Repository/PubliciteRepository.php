<?php

namespace App\Repository;

use App\Entity\Publicite;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Publicite|null find($id, $lockMode = null, $lockVersion = null)
 * @method Publicite|null findOneBy(array $criteria, array $orderBy = null)
 * @method Publicite[]    findAll()
 * @method Publicite[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PubliciteRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Publicite::class);
    }
    /**
     * Requête querybuilder
     */

    public function AffichePub(){

        return $this->createQueryBuilder('p')
            ->orderBy('p.nbrClick ','ASC')
            ->getQuery()
            ->getResult()
            ;

    }

    /**
     * Requête querybuilder
     */

    public function incrimenteNbrAffiche(){

        return $this->createQueryBuilder('p')
            ->orderBy('p.nbrAffichage ','ASC')
            ->getQuery()
            ->getEntityManager()->createQuery('UPDATE App\Entity\Publicite p SET p.nbrAffichage = p.nbrAffichage +1')
            ->getResult()

            ;

    }
    // /**
    //  * @return Publicite[] Returns an array of Publicite objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Publicite
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */

}
