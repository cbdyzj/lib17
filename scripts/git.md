# git

```sh
git config --unset credential.helper # 清除HTTP Basic密码
git clone --depth=1 <origin>
git clean -xdf # 清理文件
git remote prune origin # 清理本地分支
git stash # 储藏
git merge --squash # 合并代码
git rebase --interactive # 交互变基
git log --abbrev-commit --pretty=oneline -n10 # 简单显示最近10条提交
git push origin --delete <branch> # 删除远程分支
git branch --set-upstream-to=<ref> # 设置远程分支
git add remote <name> <ref> # 设置源
git rm --cached <file> # 删除暂存
git remote add <repository> <ref> # 增加源
git remote set-url origin <ref> # 替换源
git add -u # 暂存已跟踪文件
git add -A # 暂存所有文件
git rm --cached <filename> # 取消暂存
git tag <tagname> # 为当前版本打tag
git tag -a <tagname> <commit> -m <comment> # 为特定版本打tag
git push --tags # 推送tag到源
git push origin --delete tag <tagname> # 删除远程tag
git reset --hard <commit> # 强制回滚
git reset --hard HEAD~3 # 强制回滚三个版本
git submodule add <ref> <dir> # 增加子模块
git submodule update --init --recursive # 递归更新子模块
git verify-pack -v .git/objects/pack/pack-*.idx | sort -k 3 -g | tail -5 # 搜索大文件
git rev-list --objects --all | grep <hash> # 查看文件
git filter-branch --tree-filter 'rm -f <filename>' # 过滤历史提交
git subtree # 子树
```
