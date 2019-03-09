<?php

namespace MyHG\UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MyHGUserBundle:Template:index.html.twig');
    }
}
