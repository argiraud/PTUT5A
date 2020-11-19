# WORKFLOW GIT

## Créer sa branche de feature(qui correspond à un ticket) par rapport à la branche de dev

```
git checkout -b maNouvelleBranche dev
```

## Mettre à jour sa branche de feature sur la branche de dev

Se mettre sur sa branche de feature
```
git checkout maBranche
```

Rebase de maBranche par rapport à dev

```
git rebase origin/dev
```

Ici potentiellement des conflits (les régler dans Webstorm)

Une fois les conflits réglés on continue le rebase
```
git rebase --continue
```

Une fois le rebase terminé il faut faire un force push pour réécrire l'histoire de sa branche de feature
```
git push -f
```

## Créer une PullRequest sur Github

Il faut d'abord avoir poussé les changements sur sa branche

Ensuite sur [Github](https://github.com/MatthieuBalmont/PTUT5A/pulls)

Suivre le tuto [PullRequest](./githubPullRequest.pdf)

