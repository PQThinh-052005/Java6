<script setup>
import { ref, onMounted } from 'vue';

// URL Backend
const API_URL = 'http://localhost:8080/rest/categories';

// Danh sách danh mục
const categories = ref([]);

// Dữ liệu danh mục hiện tại (để thêm/sửa)
const category = ref({ id: '', name: '' });

// Trạng thái chỉnh sửa
const isEdit = ref(false);

// Load danh mục khi component được mount
onMounted(() => {
  fetchCategories();
});

// Lấy danh sách danh mục từ API
const fetchCategories = async () => {
  try {
    const response = await fetch(API_URL);
    categories.value = await response.json();
  } catch (error) {
    console.error('Lỗi khi lấy danh mục:', error);
  }
};

// Chọn danh mục để chỉnh sửa
const editCategory = (cat) => {
  category.value = { ...cat };
  isEdit.value = true;
};

// Thêm hoặc cập nhật danh mục
const saveCategory = async () => {
  const method = isEdit.value ? 'PUT' : 'POST';
  const url = isEdit.value ? `${API_URL}/${category.value.id}` : API_URL;

  try {
    await fetch(url, {
      method,
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(category.value),
    });

    resetForm();
    fetchCategories();
  } catch (error) {
    console.error('Lỗi khi lưu danh mục:', error);
  }
};

// Xóa danh mục
const deleteCategory = async (id) => {
  try {
    await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
    fetchCategories();
  } catch (error) {
    console.error('Lỗi khi xóa danh mục:', error);
  }
};

// Reset form
const resetForm = () => {
  category.value = { id: '', name: '' };
  isEdit.value = false;
};
</script>

<template>
  <div class="container">
    <h2>{{ isEdit ? 'Sửa Danh Mục' : 'Thêm Danh Mục' }}</h2>
    <form @submit.prevent="saveCategory">
      <input type="text" v-model="category.id" placeholder="Mã danh mục" required :disabled="isEdit" />
      <input type="text" v-model="category.name" placeholder="Tên danh mục" required />
      <button type="submit">{{ isEdit ? 'Cập Nhật' : 'Thêm Mới' }}</button>
      <button type="button" @click="resetForm">Hủy</button>
    </form>

    <h2>Danh Sách Danh Mục</h2>
    <table>
      <thead>
        <tr>
          <th>Mã</th>
          <th>Tên</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="cat in categories" :key="cat.id">
          <td>{{ cat.id }}</td>
          <td>{{ cat.name }}</td>
          <td>
            <button @click="editCategory(cat)">Sửa</button>
            <button @click="deleteCategory(cat.id)">Xóa</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
.container {
  max-width: 600px;
  margin: auto;
  text-align: center;
}

input {
  display: block;
  width: 100%;
  margin: 5px 0;
  padding: 8px;
}

button {
  margin: 5px;
  padding: 8px 12px;
  cursor: pointer;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 10px;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
}

th {
  background-color: #f4f4f4;
}
</style>
