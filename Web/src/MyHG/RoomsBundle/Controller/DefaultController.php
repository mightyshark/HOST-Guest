<?php

namespace MyHG\RoomsBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MyHGRoomsBundle::rooms.html.twig');
    }
}
