<?php
/**
 * Created by PhpStorm.
 * User: ghaith
 * Date: 12/02/2017
 * Time: 15:45
 */

namespace MyHG\ReservationBundle\Entity;
use Doctrine\ORM\Mapping as ORM;

/**

 * @ORM\Entity
 * @ORM\Table(name="reservation")

 */

class Reservation
{
    /**

     * @ORM\Id

     * @ORM\Column(type="integer")

     * @ORM\GeneratedValue(strategy="AUTO")

     */
    private $id;

    /**
     *  @ORM\ManyToOne(targetEntity="MyHG\UserBundle\Entity\Guest")
     * @ORM\JoinColumn(name="guest_id", referencedColumnName="id")
     */
    private $guestid;

    /**
     *  @ORM\ManyToOne(targetEntity="MyHG\UserBundle\Entity\Host")
     * @ORM\JoinColumn(name="host_id" ,referencedColumnName="id")
     */
    private $hostid;

    /**
     * @ORM\ManyToOne(targetEntity="MyHG\RoomsBundle\Entity\Room")
     * @ORM\JoinColumn(name="room_id" ,referencedColumnName="id")
     */
    private $roomid;


    /**
     * @ORM\Column(name="date_arrivee" ,type="date")
     */
    private $datearrivee;

    /**
     * @ORM\Column(name="date_depart" ,type="date")
     */
    private $datedepart;

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
    }

    /**
     * @return mixed
     */
    public function getGuestid()
    {
        return $this->guestid;
    }

    /**
     * @param mixed $guestid
     */
    public function setGuestid($guestid)
    {
        $this->guestid = $guestid;
    }

    /**
     * @return mixed
     */
    public function getHostid()
    {
        return $this->hostid;
    }

    /**
     * @param mixed $hostid
     */
    public function setHostid($hostid)
    {
        $this->hostid = $hostid;
    }

    /**
     * @return mixed
     */
    public function getRoomid()
    {
        return $this->roomid;
    }

    /**
     * @param mixed $roomid
     */
    public function setRoomid($roomid)
    {
        $this->roomid = $roomid;
    }

    /**
     * @return mixed
     */
    public function getDatearrivee()
    {
        return $this->datearrivee;
    }

    /**
     * @param mixed $datearrivee
     */
    public function setDatearrivee($datearrivee)
    {
        $this->datearrivee = $datearrivee;
    }

    /**
     * @return mixed
     */
    public function getDatedepart()
    {
        return $this->datedepart;
    }

    /**
     * @param mixed $datedepart
     */
    public function setDatedepart($datedepart)
    {
        $this->datedepart = $datedepart;
    }











}