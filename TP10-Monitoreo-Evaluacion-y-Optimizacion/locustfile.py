from locust import HttpUser, task, between

class Usuario(HttpUser):
    wait_time = between(1, 3)

    @task
    def login(self):
        self.client.post(
            "/login",
            json={
                "username": "admin",
                "password": "1234"
            }
        )