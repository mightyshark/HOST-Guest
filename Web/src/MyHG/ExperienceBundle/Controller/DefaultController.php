<?php

namespace MyHG\ExperienceBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('MyHGExperienceBundle:Default:index.html.twig');
    }
}
