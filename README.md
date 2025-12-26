
---

# 🧩 Lejo — Plugin for Custom Join/Quit Messages

**Lejo** is a lightweight and flexible plugin for Minecraft (Paper/Purpur) that allows full customization of join and quit messages. It supports gradients, Hex colors, formatting with placeholders, and logic based on permissions, world, nickname, and more.

---

## 🔧 Main Features

### ✅ 1. Join/Quit Messages

The plugin sends custom chat messages when players:

* **join** the server
* **leave** the server

### ✅ 2. Group-Based Messages (`parent`)

You can define different message templates for specific permission groups like `admin`, `prime`, etc.

### ✅ 3. Placeholders Support

| Placeholder             | Description                               |
| ----------------------- |-------------------------------------------|
| `{p}`                   | Player's name (nickname)                  |
| `{t}`                   | Current server time (customizable format) |
| `{w}`                   | World name the player joined or left from |
| `{w}`                   | Alias for world name                      |

and [PlaceholderApi](https://modrinth.com/plugin/placeholderapi) support! Like `%player_nick%`

### ✅ 4. Gradient & Color Formatting

You can use:

* **Hex colors**: `<color:#FF00FF>text</color>`
* **Gradients**: `<gradient:#FF00FF:#00FFFF>text</gradient>`
* **Standard MiniMessage formatting** like `<bold>`, `<italic>`, etc.

### ✅ 5. Priority System

Messages are selected based on the following order:

1. **Nickname**
2. **Parent**
3. **Default**

---
## 📄 `config.yml` Structure
[Link](https://github.com/Maksim6opka/lejo/blob/main/src/main/resources/config.yml)
---

## 🔌 Commands

| Command         | Description               | Permission     |
| --------------- | ------------------------- | -------------- |
| `/lejo`         | Show info about plugin    | -              |
| `/lejo reload`  | Reloads the plugin config | `lejo.reload`  |

---

## 💡 Example Join Message

```yaml
join:
  - "Welcome <gradient:#00FF00:#007F00>{p}</gradient> to <color:#AAAAAA>{w}</color> at {t}!"
```

→ Player **John** joined the `world_nether` at `13:42:17`
**Chat Output:** `Welcome John to nether at 13:42:17!`

---

