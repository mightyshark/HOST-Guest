<?php

namespace MyHG\ReservationBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MyHGReservationBundle:Default:index.html.twig');
    }
}
