# Release notes #
https://github.com/tri-bao/painless-gridbag/blob/master/painless-gridbag/release-notes.txt
---

Have you ever felt [totally gridbag](http://madbean.com/anim/totallygridbag/)? Lets see how the life changes with this utility: Your layout code is now like following

```
        PainlessGridBag gbl = new PainlessGridBag(getContentPane(), false);
        gbl.row().cell(lblFirstName).cell(txtFirstName).fillX()
                 .cell(lblFamilyName).cell(txtFamilyName).fillX();
        gbl.row().cell(lblAddress).cellXRemainder(txtAddress).fillX();
        gbl.doneAndPushEverythingToTop();
```

That code will produce a screen looks like

> ![http://painless-gridbag.googlecode.com/files/Screenshot-Painless-GridBag%20Demo.png](http://painless-gridbag.googlecode.com/files/Screenshot-Painless-GridBag%20Demo.png)

# How to use #
  1. Download jar file at the [download tab](http://code.google.com/p/painless-gridbag/downloads/list) and add it in the classpath
  1. All classes at the root of package **org.painlessgridbag** can be used directly. Your starting class is **PainlessGridBag**
  1. Now, lets play with it via the following "visual" tutorial
![http://painless-gridbag.googlecode.com/files/painless-gridbag-tutorial.png](http://painless-gridbag.googlecode.com/files/painless-gridbag-tutorial.png)

If you want to see constraints of each component, turn the debug flag to "true". Change the first line as following:
```
        PainlessGridBag gbl = new PainlessGridBag(getContentPane(), true);
```

The result looks like:
![http://painless-gridbag.googlecode.com/files/painless-gridbag-tutorial-debug.png](http://painless-gridbag.googlecode.com/files/painless-gridbag-tutorial-debug.png)

Now, if you want to change default constraints set by the utility, add the following code:

```
        PainlessGridbagConfiguration config = new PainlessGridbagConfiguration();
        config.setVirticalSpacing(0);
        config.setHorizontalSpacing(0);
        
        PainlessGridBag gbl = new PainlessGridBag(getContentPane(), config, false);
```

the result now looks like:
![http://painless-gridbag.googlecode.com/files/painless-gridbag-tutorial-config.png](http://painless-gridbag.googlecode.com/files/painless-gridbag-tutorial-config.png)

If you want to change GridBagConstraint of a particular component. For example x-padding of button 10, add the following lines BEFORE the last line:

```
        gbl.constraints(btn10).ipadx = 100;
```

**DO NOT FORGET** to tell the utility that you've finished defining the layout by calling one of the following method:

```
        gbl.doneAndPushEverythingToTop;
        // Or following
        //   This will put all components in the centre of the container.
        //   Normally, this method is used while defining
        //      intermediate panels (not the last one)
        gbl.done();
```

# What advantages you will get from this utility #
Lets see how cumbersome you may have faced
  * You have to write a lot of boilerplate/copy-paste code
  * You can choose to use a designer tool but then it will generate a lot of "unreadable" code
  * Then, one day, you have to add/remove/move one or several fields on the screen. It will be a nightmare
  * ... and after all, you will agree that it's [totally gridbag](http://madbean.com/anim/totallygridbag/)

With this utility, you can avoid all of these troubles. You don't need a designer tool; the code is much shorter; it's much easier to maintain the layout.

# Design principal #
  * **A utility**, not a layout manager.
  * **Light**: This utility does not need any other libraries to work. I don't want that just because of using this small utility, you have to include many other libraries!
  * **Self-descriptive API**: You only need to know the starting class. Then its API will express itself to you. That means with a minimum documentation (even no javadoc!), you can easily know how to use this utility.
  * **No textual** configuration needed.
  * **Freedom**: This utility will construct the layout with its default constraints (insets, padding...) but you can customise it whatever you want.

# What next to look for #
Although this utility saves you from the mess of layout code with a huge GridBagContraints, you still have to manage the resizing behaviour by yourself (sometime, your components collapse into a 1-pixel height! You need to set the minimum size for them). There is a modern layout manager you should check it out, called [DesignGridLayout](https://designgridlayout.dev.java.net/)
