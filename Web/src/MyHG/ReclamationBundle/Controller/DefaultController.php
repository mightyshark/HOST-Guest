<?php

namespace MyHG\ReclamationBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MyHGReclamationBundle:Default:index.html.twig');
    }
}
