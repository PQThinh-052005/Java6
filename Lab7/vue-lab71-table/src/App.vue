<template>
  <div>
    <table class="table table-dark table-striped">
      <thead>
        <tr>
          <th>Username</th>
          <th>Admin</th>
          <th>Guest</th>
          <th>User</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user, index) in users" :key="index">
          <td>{{ user.username }}</td>
          <td>
            <input type="checkbox"
              :checked="hasRole(user, 'ADMIN')"
              @change="toggleRole(user.username, 'ADMIN', $event)" />
          </td>
          <td>
            <input type="checkbox"
              :checked="hasRole(user, 'GUEST')"
              @change="toggleRole(user.username, 'GUEST', $event)" />
          </td>
          <td>
            <input type="checkbox"
              :checked="hasRole(user, 'USER')"
              @change="toggleRole(user.username, 'USER', $event)" />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";

const authorities = ref([]);

// Gọi API khi trang load
onMounted(fetchAuthorities);

// Gọi API lấy danh sách authority (username + role)
async function fetchAuthorities() {
  const res = await fetch("http://localhost:8080/api/authorities");
  authorities.value = await res.json();
}

// Gom dữ liệu thành danh sách user duy nhất + roles
const users = computed(() => {
  const map = new Map();
  for (const a of authorities.value) {
    const username = a.account.username;
    if (!map.has(username)) {
      map.set(username, {
        username,
        roles: new Set(),
      });
    }
    map.get(username).roles.add(a.role.id);
  }
  return Array.from(map.values());
});

// Kiểm tra user có role không
function hasRole(user, roleId) {
  return user.roles.has(roleId);
}

// Bật/tắt role
async function toggleRole(username, roleId, event) {
  const dto = { username, roleId };
  const method = event.target.checked ? "POST" : "DELETE";

  await fetch("http://localhost:8080/api/authorities", {
    method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(dto),
  });

  await fetchAuthorities(); // cập nhật lại danh sách
}
</script>
