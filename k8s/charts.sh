helm repo add bitnami https://charts.bitnami.com/bitnami
helm install kafka bitnami/kafka

helm repo add bitnami https://charts.bitnami.com/bitnami
helm install my-release --set auth.username=admin,auth.password=admin,auth.database="test" bitnami/mongodb